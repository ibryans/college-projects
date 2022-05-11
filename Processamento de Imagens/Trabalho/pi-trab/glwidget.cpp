#include "glwidget.h"

GLWidget::GLWidget(QWidget *parent) :
    QGLWidget(parent)
{
}

void GLWidget::initializeGL() {
}

void GLWidget::resizeGL(int width, int height) {
}

void GLWidget::paintGL() {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
}

void GLWidget::showFileOpenDialog() {
    QByteArray fileFormat = "txt";
    QString fileName;
    fileName = QFileDialog::getOpenFileName(this, "Open File", QDir::homePath(),
                                            QString("%1 Files (*.%2)").arg(QString(fileFormat.toUpper())).arg(QString(fileFormat)));
    if (!fileName.isEmpty()) {
        updateGL();
    }
}
