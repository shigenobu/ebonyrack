# ebonyrack

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Entity relationship creation tool for MariaDB.  

![image](image/image01.png)

![image](image/image02.png)

### System required

* over JAVA 17

### How to run

    java -jar ebonyrack.jar

##### environment values

| NAME        | VALUE        | EXPLANATION                                                          |
|-------------|--------------|----------------------------------------------------------------------|
| ER_ENV      | DEBUG        | If 'DEBUG', logging in detail.                                       |
| ER_READONLY | YES          | If 'YES', all projects are read only.                                |
| ER_HOME     | {your value} | If not set, `~/.ebonyrack` directory is created and used as default. |

##### args values

| NAME | VALUE | EXPLANATION                                                           |
|------|-------|-----------------------------------------------------------------------|
|--logPath=|{your value}| Path to logging file, if 'STDOUT' or 'STDERR' was set, logging there. |
|--addSeconds=|{your value}| Default 0, it means 'UTC'.                                            |

### Manual

* [Japanese](./manual/manual.ja.md)

### Dependencies

* Font: [Mplus1-Regular](https://mplusfonts.github.io/)
* Look and Feel: [FlatLaf](https://www.formdev.com/flatlaf/)

### Contributors

* [ArmadaSuit](https://github.com/ArmadaSuit)
* [Shinaco1992](https://github.com/Shinaco1992)
