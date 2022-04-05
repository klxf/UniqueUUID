# UniqueUUID
A Minecraft bukkit plugin

![Minecraft Version](https://img.shields.io/badge/Minecraft-1.18-green) ![GitHub Repo stars](https://img.shields.io/github/stars/klxf/UniqueUUID) ![release](https://img.shields.io/github/v/release/klxf/UniqueUUID) ![downloads](https://img.shields.io/github/downloads/klxf/UniqueUUID/latest/total) [![MCBBS](https://img.shields.io/badge/MCBBS-v4%20Page-green)](https://beta.mcbbs.net/resource/414eb95x)

## 关于这个插件
这是我学写插件以来的第一个插件，**很多功能还没写完**

为啥要写这个插件呢？\
是因为我的 Geyser 互通服经常有 Java 版玩家使用基岩版玩家的游戏 ID 进入服务器，某些插件（点名批评 [HuskHomes](https://github.com/WiIIiam278/HuskHomes2) ）就会因为同个 ID 有两个不同 UUID 而出现错误\
一直没有找到好的解决方案，只好从头开始学习写出了这个插件

## 插件的功能
玩家第一次进入服务器将在数据库中记录下这个玩家的 ID 和 UUID

此后玩家再进入服务器将获取其 UUID，与数据库中的进行比对，若不同则会被禁止进入服务器

## 未来的计划
- [ ] 重载命令
- [ ] 数据库不可用时采用文件储存数据