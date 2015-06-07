### ESPOTIFAI

The framework used on this program it's the classical MVC+Network, and involves 
sockets, threads and data base usage. It needs a network configuring file and mp3
files to store, to make it run, you will need a JVM and a DB. See that this is a
team project, I'm the responsible and designer of package network and everything 
related with it in the models package, I highly contributed to the DataBase 
administration and the access to this one. I did not do anything on the views of 
the client and the server.


Espotifai it's a program made to share music between a server and all his clients.
In fact an user can list and download every song stored in the server, as well as
create play lists, play his songs and contribute to a common list named PartyList
which basically it's a play list shared by everyone.
This are some screen-shots of the program:

This is the view of the login screen and the sign-UP page of the user:

![login](https://cloud.githubusercontent.com/assets/10881908/8023943/f26fc89c-0d21-11e5-92fb-c519c2e4d59f.png)

This is the main view of the client GUI and when the program it's launched:

![intro1](https://cloud.githubusercontent.com/assets/10881908/8023929/7f4981a0-0d21-11e5-9b14-6c75406dcf2f.png)

I only got one play list, named English. On the favorites tab, there are listed all
the songs from all your play lists, on the Songs tab there are all the songs stored
by the server, and on PartyList, it's the shared play list.

The buttons next to the name of the songs are to add the song to a playlist, play it
or delete it as you want, when you press the add and delete ones, there are going to 
appear a pop-up menus, which will let you indicate the proper option:

![intro 2](https://cloud.githubusercontent.com/assets/10881908/8023933/bde96934-0d21-11e5-9320-bf8eb14774d0.png)
![despues del 2](https://cloud.githubusercontent.com/assets/10881908/8023934/c0a8f838-0d21-11e5-9c4a-7a61b2d8f987.png)

On the server this is the main view, and the options it display are user gestion, to
administrate your users, music gestion, to administrate your music and view your
statistics and party list, to see the common party list:

![intro3](https://cloud.githubusercontent.com/assets/10881908/8023936/c7ba6dbe-0d21-11e5-8409-3cfaadbef36b.png)
![intro 4](https://cloud.githubusercontent.com/assets/10881908/8023937/cc6ac8b8-0d21-11e5-83be-ce956f3c9c82.png)

Everything it's stored on the database, so none of the changes made by a user are going to get lost
