from dotenv import load_dotenv  
from openai import OpenAI
import os 

load_dotenv()

OPENAI_API_KEY = os.getenv("OPENAI_API_KEY")
MEDIUM_API_TOKEN = os.getenv("MEDIUM_API_KEY")

openai_client = OpenAI()


import pandas as pd 

source = "./chatGPT/writing/"

df = pd.read_csv(f'{source}test.csv')

print(df["제목"])