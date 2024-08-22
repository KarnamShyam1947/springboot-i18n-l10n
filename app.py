from flask import Flask
from flask_cors import CORS
from translate import Translator
from flask_restx import Resource, Namespace, Api, reqparse

app = Flask(__name__)
CORS(app)

api = Api(
    app=app,
    title="Translate API",
    description="Translate english text to any language",
    validate=True,
    doc="/",
    contact="KarnamShyam1947",
    contact_url="https://karnamshyam1947.github.io/portfilio",
    contact_email="karnam.shyam2004@gmail.com",
)

translateController = Namespace(
    name="Translate",
    description="From english to any language",
    path="/translate"
)

translateMultipleController = Namespace(
    name="Translate-Multi",
    description="From english to any languages(more than one in single call)",
    path="/translate-multiple"
)

def parse_languages(languages):
    if isinstance(languages, str):
        return languages.split(',')
    return languages

translate_args = reqparse.RequestParser()
translate_args.add_argument(
    name = "text",
    type = str,
    location = "json",
    required=True,
)
translate_args.add_argument(
    name = "target_language",
    type = str,
    location = "json",
    required=True,
)

translate_multiple_args = reqparse.RequestParser()
translate_multiple_args.add_argument(
    name = "text",
    type = str,
    location = "json",
    required=True,
)
translate_multiple_args.add_argument(
    name = "target_languages",
    type=parse_languages,
    location="json",
    required=True,
)

def translate_text(text, target_language):
    translator = Translator(to_lang=target_language)
    translated_text = translator.translate(text)

    return translated_text

def multi_translate_text(text, target_languages):
    translations = {}
    
    for target_language in target_languages:
        translator = Translator(to_lang=target_language)
        translated_text = translator.translate(text.lower())
        translations[target_language] = translated_text
    
    return translations


@translateController.route("/")
class TranslateResource(Resource):
    def get(self):
        
        return {"Hello" : "World"}
    
    @translateController.expect(translate_args)
    def post(self):
        args = translate_args.parse_args()
        print(args)
        result = translate_text(args['text'], args['target_language'])

        return {"result" : result }

@translateMultipleController.route("/")
class TranslateMultipleResource(Resource):
    
    @translateMultipleController.expect(translate_multiple_args)
    def post(self):
        args = translate_multiple_args.parse_args()
        print(args)
        result = multi_translate_text(args['text'], args['target_languages'])

        return result

api.add_namespace(translateController)
api.add_namespace(translateMultipleController)

if __name__ == "__main__":
    app.run(debug=True)
