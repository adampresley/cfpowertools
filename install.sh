#! /bin/bash

#
# Ensure we only run as root.
#
if [ "$(whoami)" != "root" ]; then
	echo "You may only run this script as root."
	echo "Usage:"
	echo -e "\tsudo ./install.sh"
	echo ""
	exit 1
fi


#
# Install everything we need to run the examples and
# build docs and such.
#
echo -e "*** Installing Applications and Libraries ***"
apt-get -y -q install openjdk-6-jdk unzip ant


#
# Now start the Python script that does the rest.
#
chmod +x ./install.py
./install.py
