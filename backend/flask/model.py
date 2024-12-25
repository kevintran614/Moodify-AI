from transformers import pipeline

class Model:
    def __init__(self):
        self.classifier = pipeline("sentiment-analysis", model="michellejieli/emotion_text_classifier")

    def classifier(self, text):
        return self.classifier(text)