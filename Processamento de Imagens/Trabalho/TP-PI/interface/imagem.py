import PySimpleGUI as sg

from matplotlib import pyplot as plt;
import matplotlib.image as mpimg

import parameters
import control

elements_col_size = (60, 0)
zoom_buttons_size = (20, 0)

def tela():
    layout = [
        [sg.Text('MENU', size=(40, 1), justification='center', font=("Helvetica", 16), relief=sg.RELIEF_RIDGE, k='-TEXT HEADING-', enable_events=True)],
        [sg.Column(layout='')],[sg.Column(layout='')],[sg.Column(layout='')],
        
        [sg.Button('Selecionar região', size=elements_col_size, enable_events=True,)],
        [sg.Button('Ler imagens de treino', size=elements_col_size, enable_events=True,)],
        [sg.Button('Treinar classificador', size=elements_col_size, enable_events=True,)],
        
        [sg.Column(layout='')],[sg.Column(layout='')],[sg.Column(layout='')],
        [sg.Button('Voltar', size=elements_col_size, enable_events=True,)],
    ]
    
    window = sg.Window('Imagem selecionada', layout)
    
    while True:
        event, values = window.read()

        if event in (None, 'Sair'):
            break