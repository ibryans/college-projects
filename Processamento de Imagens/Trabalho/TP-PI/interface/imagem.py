import io
import os
import PySimpleGUI as sg

from matplotlib import pyplot as plt;
import matplotlib.image as mpimg

import parameters
import control

import tkinter.filedialog as tkf
from PIL import ImageTk, Image

from opencv import opencv

elements_col_size = (60, 0)
zoom_buttons_size = (20, 0)

def open_image(filename):
    if os.path.exists(filename):
        # opencv.abrir_imagem(filename)
        image = Image.open(filename)
        image.thumbnail((400,400))
        bio = io.BytesIO()
        image.save(bio, format="PNG")
        tela(bio.getvalue())

def tela(imagePath):

    menu_def = [
        ['&Arquivo', ['&Abrir imagem', '&Limpar', '&Sair']],
        ['&Imagem', ['&Selecionar região', '&Cortar']],
        ['&Opções', ['&Ler imagens de treino', '&Treinar classificador', '&Exibir características da imagem/região', '&Classificar imagem/região']] 
    ]

    layout = [
        [sg.MenuBar(menu_def)],
        [sg.Image(imagePath)],
        [sg.Column(layout='')],[sg.Column(layout='')],[sg.Column(layout='')],
        [sg.Text('Output')],
        [sg.Output(size=(54, 5))],
    ]
    
    window = sg.Window('Imagem selecionada', layout)
    
    while True:
        event, values = window.read()

        if event in (None, 'Fechar'):
            break

        if event == 'Abrir imagem':
            filename = os.path.abspath(tkf.askopenfilename(initialdir = os.getcwd(), title="Selecione sua imagem"))
            window.close()
            open_image(filename)
            break

        if event == 'Selecionar região':
            print('AIOA')