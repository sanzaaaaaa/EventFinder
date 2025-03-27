from flask import Flask, render_template

app = Flask(__name__)

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/amici')
def amici():
    return render_template('amici.html')

@app.route('/eventi')
def eventi():
    return render_template('eventi.html')

@app.route('/contatti')
def contatti():
    return render_template('contatti.html')

if __name__ == '__main__':
    app.run(debug=True)
