# clamav-virus-scan-service
This service can be used to scan files before uploading them to the server
# Download Clamav from https://www.clamav.net/downloads
Navigate to home directory of clamav for me it and copy clamd.conf.sample and freshclam.conf.sample from conf_examples to ClamAV
Rename to clamd.conf and freshclam.conf respectively
Edit freshclam.conf file look for Example and comment that line and uncomment following line
UpdateLogFile "C:\Program Files\ClamAV\freshclam.log"

Edit freshclam.conf file look for Example and comment that line and uncomment following lines
LogFile "C:\Program Files\ClamAV\clamd.log"
DatabaseDirectory "C:\Program Files\ClamAV\database"
Create a directory with name database in ClamAV directory

Now open cmd with Administrator access, navigate to ClamAV and run following command:
freshclam
wait for it's completion then run:
clamd
to verify if clamav is running use the following command
telent localhost 3310
in order to enable telnet in windows search "Turn windows feature on or off" and look for enable telenet and enable it

It took 16.84s for a 20.36MB Pdf file on 32 core AMD system with 32 GB ram

to instal clamav on ubuntu sudo apt update sudo apt install clamav clamav-daemon -y sudo systemctl stop clamav-freshclamclamd.conf update Db manually sudo freshclam sudo systemctl start clamav-freshclam sudo systemctl enable clamav-daemon sudo systemctl start clamav-daemon sudo systemctl status clamav-daemon netstat -tulnp | grep :3310 cp /usr/share/doc/clamav-daemon/examples/clamd.conf.sample /etc/clamav/clamd.conf edit /etc/clamav/clamd.conf uncomment port, database, and LogFile, make sure database and LogFile locations are writable edit /etc/clamav/freshclam.conf uncomment port, and LogFile, make sure database and LogFile locations are writable
