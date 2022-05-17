import io
import os
from PIL import Image
import PySimpleGUI as sg

from matplotlib import pyplot as plt;
import matplotlib.image as mpimg

import parameters
import control
from opencv import opencv

from interface import imagem

elements_col_size = (60, 0)
zoom_buttons_size = (20, 0)

def open_image(filename):
    if os.path.exists(filename):
        opencv.abrir_imagem(filename)
        # image = Image.open(filename)
        # image.thumbnail((400,400))
        # bio = io.BytesIO()
        # image.save(bio, format="PNG")
        # imagem.tela(bio.getvalue())

def start():

    layout = [
        [sg.Text('Processamento de Imagens - Trabalho Prático', size=(40, 1), justification='center', font=("Helvetica", 16), relief=sg.RELIEF_RIDGE, k='-TEXT HEADING-', enable_events=True)],
        [sg.Text('Reconhecimento de padrões por textura em imagens mamográficas', size=(60, 1), justification='center', font=("Helvetica", 10), relief=sg.RELIEF_RIDGE, k='-TEXT HEADING-', enable_events=True)],
        [sg.Column(layout='')],[sg.Column(layout='')],[sg.Column(layout='')],
        [sg.FileBrowse('Selecionar imagem', size=elements_col_size, key="select_image", enable_events=True, button_color=parameters.color_button_notselected)],
        [sg.Button('Sobre', size=elements_col_size, enable_events=True,)],
        [sg.Button('Sair', size=elements_col_size, enable_events=True,)],
    ]
    
    window = sg.Window('Aplicação', layout)
    
    while True:
        event, values = window.read()

        if event == 'select_image':
            filename = values['select_image']
            window.close()
            open_image(filename)
            imagem.tela()
            break
                

        if event in (None, 'Sair'):
            break

start()