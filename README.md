# MerlinTools

[![Issues](https://img.shields.io/github/issues/Huziyang520/MerlinTools?label=issues)](https://github.com/Huziyang520/MerlinTools/issues)
[![Releases](https://img.shields.io/github/v/release/Huziyang520/MerlinTools?label=release)](https://github.com/Huziyang520/MerlinTools/releases)
[![License](https://img.shields.io/badge/license-CC0--1.0-blue)](https://github.com/Huziyang520/MerlinTools/blob/master/LICENSE)

A testing toolkit for Minecraft: editable test weapons, an item editor, a health editor, command
macros and HUD readouts. Every part can be switched off, and the mod works on its own.

面向玩家的测试工具集：可编辑的测试武器、物品编辑、血量编辑、指令宏与 HUD 读数；每一项都能单独关闭，
模组也可以独立安装使用。

## What it does / 功能

| Part | 部件 | What it does / 作用 |
|---|---|---|
| Test weapons | 测试武器 | Sword, axe, spear and trident built as editable variants of the vanilla ones — damage and enchantments are yours to set. |
| Item editor | 物品编辑 | Rewrites the held item: enchantments, custom name and base damage. |
| Health editor | 血量编辑 | Sets maximum and current health of a mob or a player. |
| Command macros | 指令宏 | Stores command sequences and runs them from one screen. |
| HUD readouts | HUD 读数 | Damage of the weapon you just used near the crosshair, damage numbers over mobs, a folded health bar for huge health pools. |
| Switches | 开关 | Every part has its own switch; the whole toolkit can be limited to operators. |

## Install / 安装

Drop the jar into `mods/`. Fabric needs Fabric API. Cloth Config is optional: with it installed the
configuration opens in Cloth's screen, without it the mod edits its own files.

把 jar 放进 `mods/`。Fabric 端需要 Fabric API；Cloth Config 是可选的，装了才用它的配置界面，
不装就由模组自己读写配置文件。

## Configuration / 配置

The mod writes `config/MerlinTools/client.toml`, `config/MerlinTools/server.toml` and
`config/MerlinTools/macros.json`. The configuration screen opens from the mod list; changes apply on
save, no restart. NeoForge uses its own configuration page, so both files are reachable from there.

模组会生成 `config/MerlinTools/client.toml`、`server.toml` 与 `macros.json`。配置界面从模组列表打开，
保存即生效、不用重启；NeoForge 用自带的配置页，两个文件都能在那一页里改。

| Section | 分组 | Holds / 内容 |
|---|---|---|
| `hud` | HUD | Crosshair damage and how long it stays, folded health bar, armour row placement. |
| `floating_text` | 伤害飘字 | Damage numbers: on/off, duration, scale, colours, how many at once. |
| `editor` | 物品编辑 | Editor hotkey, value step for shift-click and for the mouse wheel. |
| `macros` | 指令宏 | Master switch of the macro screen. |
| `security` | 权限 | Operator-only mode, permission needed to open the item editor and the health editor. |
| `tools` | 测试工具 | Damage and enchantment ceilings, which items the editor accepts, health editor requirements. |

## Links / 链接

- Source, releases and issue tracker: <https://github.com/Huziyang520/MerlinTools>
- Backup feedback address: <https://issue.mengcai.online/>

## Licence / 许可证

CC0 1.0 Universal — do whatever you want with it. Copyright (c) 2026 Huziyang520.
