#! /usr/bin/python
import sys, os, subprocess
import pwd, re


#
# The following variables control the basics of where you wish to put things.
# It also defines the user that will own what is installed. Change this
# to match your user account name.
#
USER = "psykoprogrammer"
WHEREAT = os.getcwd()

BIN = "./bin"


TOMCAT_HOSTS = """
\t\t<Context path="" docBase="{0}/examples" />
\t</Host>
""".format(WHEREAT)


GROOVY_LANGUAGE_FILE = """
Language: Groovy

   Extension: groovy
   Line Comment: //
   Block Comment: /* */
   Enum Values: Under type
   Function Prototype Ender: {
   Variable Prototype Enders: ; =
"""



#
# Grab info about the user that will own
# most of this.
#
userInfo = pwd.getpwnam(USER)
userName = userInfo.pw_name
uid = userInfo.pw_uid
gid = userInfo.pw_gid


#
# Ensure we only run as root.
#
if subprocess.check_output(["whoami"]).strip() != "root":
	print "You may only run this script as root."
	print "Usage:"
	print "\tsudo ./install.sh"
	print ""
	sys.exit(1)


#
# Install necessary applications
#
if not os.path.exists(BIN):
	subprocess.check_output(["mkdir", BIN])
	subprocess.check_output(["chown", "-R", "{0}:{0}".format(USER), BIN])


if not os.path.exists("%s/apache-tomcat-7.0.27" % (BIN)):
	print "\n*** Installing Tomcat ***"

	subprocess.check_output(["wget", "http://apache.petsads.us/tomcat/tomcat-7/v7.0.27/bin/apache-tomcat-7.0.27.tar.gz"])
	subprocess.check_output(["tar", "-zxvf", "./apache-tomcat-7.0.27.tar.gz"])
	subprocess.check_output(["mv", "./apache-tomcat-7.0.27", "%s/apache-tomcat-7.0.27" % (BIN)])
	subprocess.check_output(["ln", "-s", "%s/apache-tomcat-7.0.27" % (BIN), "./tomcat"])
	subprocess.check_output(["rm", "./apache-tomcat-7.0.27.tar.gz"])

	subprocess.check_output(["chown", "-R", "{0}:{0}".format(USER), "%s/apache-tomcat-7.0.27" % (BIN)])
	subprocess.check_output(["chown", "-R", "{0}:{0}".format(USER), "./tomcat"])


if not os.path.exists("%s/NaturalDocs-1.52" % (BIN)):
	print "\n*** Installing NaturalDocs ***"

	subprocess.check_output(["wget", "http://downloads.sourceforge.net/project/naturaldocs/Stable%20Releases/1.52/NaturalDocs-1.52.zip"])
	subprocess.check_output(["unzip", "./NaturalDocs-1.52", "-d", "NaturalDocs-1.52"])
	subprocess.check_output(["mv", "./NaturalDocs-1.52", "%s/NaturalDocs-1.52" % (BIN)])
	subprocess.check_output(["ln", "-s", "%s/NaturalDocs-1.52" % (BIN), "./naturaldocs"])
	subprocess.check_output(["rm", "./NaturalDocs-1.52.zip"])

	subprocess.check_output(["chown", "-R", "{0}:{0}".format(USER), "%s/NaturalDocs-1.52" % (BIN)])
	subprocess.check_output(["chown", "-R", "{0}:{0}".format(USER), "./naturaldocs"])
	subprocess.check_output(["chmod", "+x", "./naturaldocs/NaturalDocs"])


#
# Alter the Tomcat config file to have host entries
# for the example applications.
#
r = open("./tomcat/conf/server.xml", "r")
contents = r.read()
r.close()

found = "<Context" in contents

if not found:
	print "\n*** Configuring Tomcat ***"
	
	w = open("./tomcat/conf/server.xml", "w")
	p = re.compile(r'</host>', re.IGNORECASE)

	altered = p.sub(r'{0}'.format(TOMCAT_HOSTS), contents)
	w.write(altered)
	w.close()


#
# Fix the fact NaturalDocs has no syntax definitions
# for Groovy
#
r = open("./naturaldocs/Config/Languages.txt", "r")
contents = r.read()
r.close()

found = GROOVY_LANGUAGE_FILE in contents

if not found:
	print "\n*** Updating NaturalDocs Language Definitions ***"

	w = open("./naturaldocs/Config/Languages.txt", "w")
	p = re.compile(r'Language: Java\n', re.IGNORECASE)

	altered = p.sub(r'{0}\n\nLanguage: Java'.format(GROOVY_LANGUAGE_FILE), contents)
	w.write(altered);
	w.close()

