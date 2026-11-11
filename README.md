# 🧰 MerlinTools

[![GitHub](https://img.shields.io/badge/GitHub-MerlinTools-181717?logo=github)](https://github.com/Huziyang520/MerlinTools)
[![Issues](https://img.shields.io/github/issues/Huziyang520/MerlinTools?color=2ea043)](https://github.com/Huziyang520/MerlinTools/issues)
[![License](https://img.shields.io/badge/license-CC0--1.0-4c9be8)](https://github.com/Huziyang520/MerlinTools/blob/master/LICENSE)

[English](#english) | [中文](#中文)

---

## English

A testing toolkit for Minecraft: editable test weapons, an item editor, a health editor, command macros and HUD readouts. Every part can be switched off, and the mod works on its own.

### ✨ What it does

| Part | What it does |
|---|---|
| Test weapons | Sword, axe, spear and trident built as editable variants of the vanilla ones — damage and enchantments are yours to set. |
| Item editor | Rewrites the held item: enchantments, custom name and base damage. |
| Health editor | Sets maximum and current health of a mob or a player. |
| Command macros | Stores command sequences and runs them from one screen. |
| HUD readouts | Damage of the weapon you just used near the crosshair, damage numbers over mobs, a folded health bar for huge health pools. |
| Switches | Every part has its own switch; the whole toolkit can be limited to operators. |

### 📥 Install

Drop the jar into `mods/`. Fabric needs Fabric API. Cloth Config is optional: with it installed the configuration opens in Cloth's screen, without it the mod edits its own files.

### ⚙️ Configuration

The mod writes `config/MerlinTools/client.toml`, `config/MerlinTools/server.toml` and `config/MerlinTools/macros.json`. The configuration screen opens from the mod list; changes apply on save, no restart. NeoForge uses its own configuration page, so both files are reachable from there.

| Section | Holds |
|---|---|
| `hud` | Crosshair damage and how long it stays, folded health bar, armour row placement. |
| `floating_text` | Damage numbers: on/off, duration, scale, colours, how many at once. |
| `editor` | Editor hotkey, value step for shift-click and for the mouse wheel. |
| `macros` | Master switch of the macro screen. |
| `security` | Operator-only mode, permission needed to open the item editor and the health editor. |
| `tools` | Damage and enchantment ceilings, which items the editor accepts, health editor requirements. |

### 🔗 Links

- Source and issue tracker: <https://github.com/Huziyang520/MerlinTools>
- Backup feedback address: <https://issue.mengcai.online/>

### 📜 Licence

CC0 1.0 Universal — do whatever you want with it. Copyright (c) 2026 Huziyang520.

---

## 中文

一款面向 Minecraft 的测试工具集：可编辑的测试武器、物品编辑、血量编辑、指令宏与 HUD 读数。每一项都能单独关闭，模组也可以独立安装使用。

### ✨ 功能

| 部件 | 作用 |
|---|---|
| 测试武器 | 剑 / 斧 / 长矛 / 三叉戟，原版武器的可编辑变体，伤害与附魔都可以自己设。 |
| 物品编辑 | 改写手持物品：附魔、自定义名称与基础伤害。 |
| 血量编辑 | 设置生物或玩家的最大生命值与当前生命值。 |
| 指令宏 | 保存一串指令，在一个界面里执行。 |
| HUD 读数 | 准心旁显示刚用过武器的伤害、生物头顶的伤害飘字、超高血量时的折叠血条。 |
| 开关 | 每个部件都有独立开关；整套工具可以限制为仅管理员可用。 |

### 📥 安装

把 jar 放进 `mods/`。Fabric 端需要 Fabric API；Cloth Config 是可选的，装了才用它的配置界面，不装就由模组自己读写配置文件。

### ⚙️ 配置

模组会生成 `config/MerlinTools/client.toml`、`config/MerlinTools/server.toml` 与 `config/MerlinTools/macros.json`。配置界面从模组列表打开，保存即生效、不用重启；NeoForge 用自带的配置页，两个文件都能在那一页里改。

| 分组 | 内容 |
|---|---|
| `hud` | 准心伤害与其停留时长、折叠血条、护甲行位置。 |
| `floating_text` | 伤害飘字：开关、时长、缩放、颜色、同时显示数量。 |
| `editor` | 编辑热键、Shift 点击与滚轮的数值步长。 |
| `macros` | 宏界面的总开关。 |
| `security` | 仅管理员模式、打开物品编辑与血量编辑所需权限。 |
| `tools` | 伤害与附魔等级上限、编辑器接受哪些物品、血量编辑的前置条件。 |

### 🔗 链接

- 源码与问题反馈：<https://github.com/Huziyang520/MerlinTools>
- 备用反馈地址：<https://issue.mengcai.online/>

### 📜 许可证

本项目以 CC0 1.0 协议开源，可自由使用。Copyright (c) 2026 Huziyang520.
