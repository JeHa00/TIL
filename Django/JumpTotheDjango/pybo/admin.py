from django.contrib import admin
from .models import Question, Answer
from typing import Sequence
# Register your models here.


class QuestionAdmin(admin.ModelAdmin):
    search_fields: Sequence[str] = ['subject']


class AnswerAdmin(admin.ModelAdmin):
    search_fields: Sequence[str] = ['content']


admin.site.register(Question, QuestionAdmin)
admin.site.register(Answer, AnswerAdmin)
