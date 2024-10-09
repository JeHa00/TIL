from chatGPT.client import client 
import json

def get_current_weather(location, unit="fahrenheit"):
    """Get the current weather in a given location"""
    if "tokyo" in location.lower():
        return json.dumps({"location": "Tokyo", "temperature": "10", "unit": unit})
    elif "san francisco" in location.lower():
        return json.dumps({"location": "San Francisco", "temperature": "72", "unit": unit})
    elif "paris" in location.lower():
        return json.dumps({"location": "Paris", "temperature": "22", "unit": unit})
    else:
        return json.dumps({"location": location, "temperature": "unknown"})
    
def run_conversation():
    messages = [{"role": "user",
                 "content": "What's the weather like in San Francisco, Tokyo, and Paris?"
                 }]
    tools = [
        {
        "type": "function", 
        "function": {
            "name": "get_current_weather",
            "description": "Get the current weather in a given location",
            "parameters": {
                "type": "object",
                "properties": {
                    "location": {   
                        "type": "string",
                        "description": "The city and state, e.g. San Francisco, CA",
                    },
                    "unit": {"type": "string", "enum": ["celsius", "fahrenheit"]},
                },
                "required": ["location"],
            },
        }
    }]

    response = client.chat.completions.create(
        model="gpt-4o",
        messages=messages, 
        tools=tools,
        # tool_choice="auto",
    )
    
    response_message = response.choices[0].message
    print(f"first response: {response_message}")

    tool_calls = response_message.tool_calls # 세 위치에 대해서 물어봤기 때문에 3개의 날씨 답변을 보내줬다. 

    if tool_calls:
        available_functions = {"get_current_weather": get_current_weather,}
        messages.append(response_message)


        for tool_call in tool_calls:
            function_name = tool_call.function.name
            function_to_call = available_functions[function_name]
            function_args = json.loads(tool_call.function.arguments)
            function_response = function_to_call(
                location=function_args.get("location"),
                unit=function_args.get("unit"),
            )
            messages.append(
                {
                    "tool_call_id": tool_call.id,
                    "role": "tool",
                    "name": function_name,
                    "content": function_response,
                }
            )  # extend conversation with function response
        second_response = client.chat.completions.create(
            model="gpt-4o",
            messages=messages,
        )  # get a new response from tsee the function responsehe model where it can 
        return second_response.choices[0].message

print(run_conversation())

