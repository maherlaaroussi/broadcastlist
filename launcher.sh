#!/bin/bash

javac -classpath mail.jar serveur/*.java pro/diff/*.java Main.java && java -classpath .:mail.jar Main
