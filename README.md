# cs3219-tutorial6

**Setting up project**<br>
1. Download [HornetQ 2.4.0 Final](http://downloads.jboss.org/hornetq/hornetq-2.4.0.Final-bin.zip)<br>
2. Unzip the zip package to any location on your machine<br>
3. Clone this repo<br>
4. Copy `hornetq-users.xml` and `hornetq-jms.xml` from `cs3219-tutorial6/server` folder to the `config/stand-alone/non-clustered` folder found in your **HornetQ** directory, i.e. `/some-directory-on-your-pc/hornetq-2.4.0.Final/config/stand-alone/non-clustered`<br>

**Edit launch scripts**<br>
5. Make a copy of the launch script and rename it to `launch-hornetq-server.bat` <br>
6. Edit the launch script to point to the directory of your HornetQ folder.
e.g.

    SET HORNETQ_HOME=C:\somewhere-on-your-ps\hornetq-2.4.0.Final
     
<br>
7. Execute `launch-hornetq-server.bat` to run HornetQ server.



