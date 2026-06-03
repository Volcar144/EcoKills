
![Logo](https://i.postimg.cc/vDRzQ6tH/Untitled-design-(1).png)


# EcoKills

A Minecraft plugin to give a percentage of a player's money to another player when they have been killed.


[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)





## Features

- Configurable design
- Percentage rate or flat fee 
- Reload command


## Installation

Install the plugin on paper version 26.1.2 and edit the config if necessary. It supports placeholderAPI and requires the newest version of Vault and a compatiable economy plugin to work.

Config:
```yml
    #Show the ASCII banner when the plugin is enabled
    ascii-banner: true

    payments:
    #Pay a flat fee for each kill, or a percentage of the victim's balance
    flat-fee: false
    fee-amount: 100

    #Max 100
    percentage: 10

    #Messages in the plugin use the minimessage format(https://webui.advntr.dev/). If you have placeholderAPI enabled, you can use that too.
    #Placeholders:
    # - %victim% the person who was killed
    # - %killer% the killer of the player
    # - %amount% the amount earned.
    messages:
    killer: "<gold>You killed <red>%victim%</red> and earned <red>%amount%</red></gold>"
    victim: "<red>You were killed by <gold>%killer%</gold> and lost <gold>%amount%</gold></red>"

```
    
## Authors

- [@Volcar144](https://www.github.com/Volcar144)

