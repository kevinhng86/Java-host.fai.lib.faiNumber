#!/bin/sh
# LINUX BUILD.SH - UBUNTU 18

# This build script does not verify the files in the repo.

########################################################################
#                BUILD AND BOOTCLASSPATH NOTE                          #
#                                                                      #
# For 7 and 8.                                                         # 
#                                                                      #
# Every system may have different settings, this is just               #
# to demonstrate.                                                      #
#                                                                      #
# 7   - /usr/lib/jvm/jdk1.7.0_80/jre/lib/rt.jar                        #
# 8   - /usr/lib/jvm/java-1.8.0-openjdk-amd64/jre/lib/rt.jar           #
#                                                                      #
# For 9+, choose the correct version of the Jdk that installed and     #
# active.                                                              #
########################################################################

if ! [ -x "$(command -v javac)" ]; then
  echo 'Error: Java compiler is not installed.'
  exit 1
fi

Readme_File="README.md"
License_File="LICENSE"

if [ ! -f $License_File ] ; then
   echo "Error: License file($License_File) does not exist."
   exit 1
fi

if [ ! -f $Readme_File ] ; then
   echo "Error: Readme file($Readme_File) does not exist."
   exit 1
fi

printf "Automatic build script for host.fai.lib.faiNumber\n\n\n"

# VARS
Version="1.0.0.f"
Source_Dir="./src/*.java"
Target_Dir="."
Built_By=""
Build_Target_Jdk=""
Build_Desc=""
Build_Jdk=$(javac -version 2>&1| cut -d ' ' -f2)
Build_Timestamp=$(date +%s%N)
Package_Name="host.fai.lib.fainumber"
Project_Inception_Year="2019"
Project_Desc="A library that mainly deals with converting strings of number to a supported primitive data type, numerically validating strings or chars, numerically comparing strings, and converting a supported primitive data type to numerical strings."
Project_Repo_Home="https://github.com/kevinhng86/Java-host.fai.lib.faiNumber"
Documentation="https://lib.fai.host/java/faiNumber/v1"
bootclasspath="";

while [ -z "$Built_By" ];
do
   echo "Built-By?:"
   read -r Built_By
done

while true;
do
   echo "Target JDK Version(7, 8, 9, 10, or 11):"
   read -r Build_Target_Jdk
   
   # JDK 7
   if [ "$Build_Target_Jdk" = "7" ]; then
      Build_Target_Jdk="1.7"
      Build_Desc="Java 7+"
      break
   fi

   # JDK 8
   if [ "$Build_Target_Jdk" = "8" ]; then
      Build_Target_Jdk="1.8"
      Build_Desc="Java 8+"
      Version="${Version}-8"
      break
   fi

   # JDK 9   
   if [ "$Build_Target_Jdk" = "9" ]; then
      Build_Target_Jdk="9"
      Build_Desc="Java 9+"
      Version="${Version}-9"
      break
   fi

   # JDK 10
   if [ "$Build_Target_Jdk" = "10" ]; then
      Build_Desc="Java 10+"
      Version="${Version}-10"
      break
   fi

   # JDK 11
   if [ "$Build_Target_Jdk" = "11" ]; then
      Build_Desc="Java 11+"
      Version="${Version}-11"
      break
   fi   
done

echo "bootclasspath or blank:"
read bootclasspath;

if [ ! $bootclasspath = "" ]; then
    bootclasspath="-bootclasspath $bootclasspath"
fi   

printf "\n\n\n"
echo "*****Compiling Java Source Code*****"
Compiler_Output=$(javac -d $Target_Dir $Source_Dir -source $Build_Target_Jdk -target $Build_Target_Jdk $bootclasspath) || exit 1 
if [ ! $Compiler_Output = "" ]; then
   printf "Error: Complication failed.\n%s" "$Compiler_Output"
   exit 1
fi

Temp_INF=$(date +%s%N).INF.tmp

touch $Temp_INF
echo "Built-By: $Built_By" >> $Temp_INF
echo "Build-Target-Jdk: $Build_Target_Jdk" >> $Temp_INF
echo "Build-Desc: $Build_Desc" >> $Temp_INF
echo "Build-Jdk: $Build_Jdk" >> $Temp_INF
echo "Build-Timestamp: $Build_Timestamp" >> $Temp_INF
echo "Package-Name: $Package_Name" >> $Temp_INF
echo "Package-Version: $Version" >> $Temp_INF
echo "Project-Inception-Year: $Project_Inception_Year" >> $Temp_INF
echo "Project-Desc: $Project_Desc" >> $Temp_INF
echo "Project-Repo-Home: $Project_Repo_Home" >> $Temp_INF
echo "Documentation: $Documentation" >> $Temp_INF

mkdir ./META-INF 
cp $Readme_File ./META-INF
cp $License_File ./META-INF

jar -cmvf $Temp_INF faiNumber-${Version}.jar ./host ./META-INF 

rm $Temp_INF
rm -r META-INF
rm -r host
