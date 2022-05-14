import PySimpleGUI as sg

from matplotlib import pyplot as plt;
import matplotlib.image as mpimg

import parameters
import control
from opencv import opencv

from interface import imagem

elements_col_size = (60, 0)
zoom_buttons_size = (20, 0)

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
      
    if event == 'Display':
        # Update the "output" text element
        # to be the value of "input" element
        window['-OUTPUT-'].update(values['-IN-'])

    if event == 'select_image':
            imgPath = values['select_image']

            if imgPath != "": 
                control.image_cropped = False
                control.image_checked = False
                window.close()
                opencv.abrir_imagem(imgPath)
                imagem.tela()

    if event in (None, 'Sair'):
        break
  
window.close()