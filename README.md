# MotenoServer
Server for [Simple app with everyday Quotes](https://github.com/shadoweG/Moteno), made by [@BoyBacks](https://github.com/BoyBACKS)

## Setup
- Create a folder ex. MotenoServer
- Download jar file (I assume you installed java already)
- Download screen, node & json-server package
- Create a screen `screen -R moteno-chnager`
- Launch java changer `java -jar MotenoServer-1.0.jar`
- Exit the screen (Ctrl + a + d)
- Create a screen `screen -R moteno`
- Launch json-server `npx json-server --host yourip --watch quote.json`
- Change "http://yourip:3000" inside [Json.swift](https://github.com/shadoweG/Moteno/blob/main/Moteno/Json.swift) to your public server ip.

## Contributing
Pull requests are always welcome. For bigger changes, please open an issue first to discuss what you would like to change.

## License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
