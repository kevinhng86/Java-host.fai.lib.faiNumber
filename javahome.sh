#!/bin/sh
# JAVA_HOME SWITCHER

# This is for building multi version, reconfig to necessary.
# . ./javahome.sh  <-

printf "********JAVA_HOME SWITCHER********\n\n\n"
J7=/usr/lib/jvm/jdk1.7.0_80
J8=/usr/lib/jvm/java-1.8.0-openjdk-amd64
J9=/usr/lib/jvm/openjdk-9.0.4_linux-x64_bin
J10=/usr/lib/jvm/openjdk-10.0.2_linux-x64_bin
J11=/usr/lib/jvm/jdk-11.0.2

while true;
do
   echo "Swich JAVA_HOME to(7, 8, 9, 10, or 11):"
   read -r Jdk_Version
   
   # JDK 7
   if [ "$Jdk_Version" = "7" ]; then
      export JAVA_HOME=$J7
      break
   fi

   # JDK 8
   if [ "$Jdk_Version" = "8" ]; then
      export JAVA_HOME=$J8
      break
   fi

   # JDK 9   
   if [ "$Jdk_Version" = "9" ]; then
      export JAVA_HOME=$J9
      break
   fi

   # JDK 10
   if [ "$Jdk_Version" = "10" ]; then
      export JAVA_HOME=$J10
      break
   fi

   # JDK 11
   if [ "$Jdk_Version" = "11" ]; then
      export JAVA_HOME=$J11
      break
   fi   
done

echo "Switched to JDK $Jdk_Version with JAVA_HOME=$JAVA_HOME"
