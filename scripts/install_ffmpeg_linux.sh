#!/bin/bash
echo "Aide a l'installation de FFMPEG sous Linux"
if [ "$(whoami)" != "root" ]
then
	echo "Le package FFMPEG n'est pas installe, pour l'installer merci de taper votre mot de passe :"
	exec sudo bash "$0" "$@"
	apt-get install ffmpeg
else 
	echo "Vous avez les privileges administrateur, demarrage de l'installation"
	apt-get install ffmpeg
fi

