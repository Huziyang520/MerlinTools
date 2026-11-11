# MerlinTools — Usage

[English](#english) | [中文](#中文)

> Configuration files, commands, items and where the configuration screen comes from.

---

## English

### Where everything lives

| Line | Client switches | Server switches |
|---|---|---|
| NeoForge | `config/merlintools-client.toml` | `config/merlintools-common.toml` |
| Fabric | `config/MerlinTools/client.toml` | `config/MerlinTools/server.toml` |
| Forge | `config/MerlinTools/client.toml` | `config/MerlinTools/server.toml` |

| File | Purpose | How a change applies |
|---|---|---|
| `client.toml` | HUD, damage numbers, the editor's feel and the macros | saving is enough |
| `server.toml` | who may use the toolkit, and what the editors are allowed to do | saving is enough |
| `macros.json` | the macro list, written by the macro screen | saving is enough |

The values are read as they are used, so a switch takes effect as soon as the file is saved. The server
file is authoritative: the client only shows what the server allows, and a refused write is answered in
chat. In single player the integrated server shares the process, so a change applies at once.

### `client.toml`

| Key | Default | Meaning |
|---|---|---|
| `hud.crosshair_damage` | `false` | show the damage figure of the weapon you just swung near the crosshair |
| `hud.crosshair_damage_ticks` | `20` | how long that figure stays, in ticks |
| `hud.collapse_enabled` | `true` | fold a very large health bar into one heart plus the numbers |
| `hud.collapse_threshold` | `99` | health above which the bar folds; a normal player bar is untouched |
| `hud.armour_auto_dodge` | `true` | move the armour row up onto the folded bar |
| `floating_text.enabled` | `false` | damage numbers that fly off a creature when it is hit |
| `floating_text.duration_ticks` | `20` | their lifetime, in ticks |
| `floating_text.base_scale` | `1.0` | their base size |
| `floating_text.normal_color` / `critical_color` | `0xFFFFFFFF` / `0xFFFFAA00` | colour of a normal and of a critical hit |
| `floating_text.max_concurrent` | `32` | at most this many at once; the oldest is dropped |
| `editor.hotkey_enabled` | `false` | whether the hotkey may open the item editor |
| `editor.shift_step` | `10` | how much a shift click on `-` / `+` changes a value |
| `editor.scroll_step` / `scroll_step_fast` | `1` / `10` | how much one wheel notch over a row changes it, and the same with control held |
| `macros.enabled` | `true` | the macro screen and running macros from their keys |
| `general.config_version` | current | file layout marker, written by the mod; not meant to be edited |

Colours accept `0xRRGGBB`, `#RRGGBB`, `RRGGBB` or a palette name (`red`, `cyan`, `pink`, ...).

### `server.toml`

| Key | Default | Meaning |
|---|---|---|
| `security.restrict_tools_to_operators` | `false` | only operators may use the toolkit |
| `security.editor_requires_permission` | `true` | the item editor needs command permission |
| `security.health_editor_requires_permission` | `true` | the health editor needs command permission |
| `tools.enable_testing_toolkit` | `true` | master switch: with it off no toolkit screen opens and the creative tab no longer offers the items |
| `tools.max_test_weapon_damage` | integer limit | ceiling for the damage of a test weapon |
| `tools.max_enchantment_level` | integer limit | ceiling for a level set through the item editor |
| `tools.allow_levels_above_max` | `true` | allow a level above the one the enchantment itself declares |
| `tools.allow_mismatched_enchantments` | `false` | allow any enchantment on any item |
| `tools.allow_editing_all_items` | `false` | let the item editor open for every item, not only weapons, tools and armour |
| `tools.allow_non_test_item_damage` | `false` | allow the damage row on items that are not test weapons |
| `tools.health_editor_requires_item` | `true` | the health editor opens only while its own item is held |

### Commands

| Command | What it does |
|---|---|
| `/merlintools editor` | opens the item editor for the held item |
| `/merlintools health` | opens the health editor for yourself |
| `/merlintools macro` | opens the command macro screen |
| `/merlintools config` | opens the configuration screen |

These are client-side commands: they open windows, and every write they cause is checked by the server.

Two keys live in **Controls** and can be rebound there: one opens the item editor (it does nothing while
`editor.hotkey_enabled` is off) and one opens the macro screen. A macro's own key is bound inside the
macro screen, and several keys may be held together.

### Items

| Item | Id |
|---|---|
| Test sword | `merlintools:test_sword` |
| Test axe | `merlintools:test_axe` |
| Test spear | `merlintools:test_spear` |
| Test trident | `merlintools:test_trident` |
| Health editor | `merlintools:health_editor` |

All of them sit in the **Tools & Utilities** creative tab. The test weapons are editable variants of the
vanilla ones — the item editor changes their name, their enchantments and their base damage.

The health editor is used in hand: sneak and use it on a creature to open the editor for that creature.

Not every line ships every item; one that is missing on your line simply is not there.

### The configuration screen

| Line | How the screen is reached |
|---|---|
| NeoForge | **Config** in the mods list, or `/merlintools config` — NeoForge's own page |
| Fabric | `/merlintools config` or the mod menu entry, drawn by **Cloth Config**; on a client with MezzConfig instead, its own page |
| Forge | **Config** in the mods list, or `/merlintools config`, drawn by **Cloth Config** |

The screen lists the client switches first, then `security` and `tools` from the server file, in the
order this guide lists them.

### Dependencies

- **Fabric:** Fabric API.
- **Cloth Config** is optional. Without it, `/merlintools config` says so and the switches are read from
  and written to the files, so nothing is lost except the window — the commands and the editors work
  either way.
- Nothing else is required, and MerlinTools does not need a library mod to run.

---

## 中文

### 文件都在哪

| 线 | 客户端开关 | 服务端开关 |
|---|---|---|
| NeoForge | `config/merlintools-client.toml` | `config/merlintools-common.toml` |
| Fabric | `config/MerlinTools/client.toml` | `config/MerlinTools/server.toml` |
| Forge | `config/MerlinTools/client.toml` | `config/MerlinTools/server.toml` |

| 文件 | 作用 | 生效方式 |
|---|---|---|
| `client.toml` | HUD、伤害飘字、编辑手感与宏 | 保存即可 |
| `server.toml` | 谁能用这套工具、编辑器允许做到什么 | 保存即可 |
| `macros.json` | 宏列表，由宏界面写入 | 保存即可 |

这些值都是**用的时候才读**，所以开关一保存就生效。服务端文件是权威：客户端只展示服务端允许的范围，
被拒绝的写入会在聊天栏告知一次。单人游戏下集成服务端与本进程共享，改动立即生效。

### `client.toml`

| 键 | 默认 | 说明 |
|---|---|---|
| `hud.crosshair_damage` | `false` | 在准星附近显示刚挥出的武器的伤害数值 |
| `hud.crosshair_damage_ticks` | `20` | 该数值停留时长（游戏刻） |
| `hud.collapse_enabled` | `true` | 把很长的血条折叠成一颗心加数值 |
| `hud.collapse_threshold` | `99` | 超过这个生命值才折叠；普通玩家的血条不受影响 |
| `hud.armour_auto_dodge` | `true` | 折叠后护甲行上移，紧贴折叠血条 |
| `floating_text.enabled` | `false` | 生物受伤时头顶飞出的伤害数字 |
| `floating_text.duration_ticks` | `20` | 持续时间（游戏刻） |
| `floating_text.base_scale` | `1.0` | 基础字号 |
| `floating_text.normal_color` / `critical_color` | `0xFFFFFFFF` / `0xFFFFAA00` | 普通命中与暴击的颜色 |
| `floating_text.max_concurrent` | `32` | 同时存在的上限，超出时丢弃最早的 |
| `editor.hotkey_enabled` | `false` | 快捷键是否可以打开物品编辑 |
| `editor.shift_step` | `10` | 按住 Shift 点 `-` / `+` 时的步长 |
| `editor.scroll_step` / `scroll_step_fast` | `1` / `10` | 鼠标停在某行上滚轮每格的变化量，以及按住 Ctrl 时的 |
| `macros.enabled` | `true` | 宏界面与按键触发（总开关） |
| `general.config_version` | 当前值 | 文件布局标记，由模组写入，不必手改 |

颜色支持 `0xRRGGBB`、`#RRGGBB`、`RRGGBB` 或调色板名（`red`、`cyan`、`pink` …）。

### `server.toml`

| 键 | 默认 | 说明 |
|---|---|---|
| `security.restrict_tools_to_operators` | `false` | 仅 OP 可使用这套工具 |
| `security.editor_requires_permission` | `true` | 物品编辑需要命令权限 |
| `security.health_editor_requires_permission` | `true` | 血量编辑需要命令权限 |
| `tools.enable_testing_toolkit` | `true` | 总开关：关闭后任何工具界面都不打开，创造页也不再提供这些物品 |
| `tools.max_test_weapon_damage` | int 上限 | 测试武器的伤害上限 |
| `tools.max_enchantment_level` | int 上限 | 物品编辑可设置的附魔等级上限 |
| `tools.allow_levels_above_max` | `true` | 允许突破附魔自身定义的最大等级 |
| `tools.allow_mismatched_enchantments` | `false` | 允许把任意附魔加到任意物品上 |
| `tools.allow_editing_all_items` | `false` | 允许对所有物品启用物品编辑（不止武器 / 工具 / 装备） |
| `tools.allow_non_test_item_damage` | `false` | 允许非测试武器也出现伤害行 |
| `tools.health_editor_requires_item` | `true` | 血量编辑需要手持对应物品 |

### 指令

| 指令 | 作用 |
|---|---|
| `/merlintools editor` | 打开手持物品的物品编辑界面 |
| `/merlintools health` | 打开自己的血量编辑界面 |
| `/merlintools macro` | 打开指令宏界面 |
| `/merlintools config` | 打开配置界面 |

这些都是客户端指令：它们只负责开界面，由此产生的每次写入都由服务端校验。

两个按键在**设置 → 按键绑定**里，可以自行改键：一个开物品编辑（`editor.hotkey_enabled` 关闭时按了没有反应），
一个开指令宏界面。宏自身的按键在宏界面里绑定，可以多个键一起按。

### 物品

| 物品 | id |
|---|---|
| 测试剑 | `merlintools:test_sword` |
| 测试斧 | `merlintools:test_axe` |
| 测试长矛 | `merlintools:test_spear` |
| 测试三叉戟 | `merlintools:test_trident` |
| 血量编辑器 | `merlintools:health_editor` |

它们都在创造模式的**工具与实用物品**页。测试武器是原版武器的可编辑变体——物品编辑改的是它们的名称、
附魔与基础伤害。

血量编辑器拿在手上用：潜行时对生物使用，即可打开那只生物的血量编辑。

并非每条线都提供全部物品；你那条线上没有的物品就是没有。

### 配置界面

| 线 | 打开方式 |
|---|---|
| NeoForge | 模组列表里的 **配置（Config）**，或 `/merlintools config`——NeoForge 自己的页面 |
| Fabric | `/merlintools config` 或模组菜单入口，由 **Cloth Config** 绘制；装了 MezzConfig 的客户端则用它的配置页 |
| Forge | 模组列表里的 **配置**，或 `/merlintools config`，由 **Cloth Config** 绘制 |

界面先列客户端开关，然后是服务端文件的 `security` 与 `tools`，顺序与本文一致。

### 依赖

- **Fabric** 需要 Fabric API。
- **Cloth Config** 是可选的。没装时 `/merlintools config` 会说明这一点，开关仍在文件里读写，
  除了窗口不会少任何东西——指令与各个编辑界面照常可用。
- 别的不需要，MerlinTools 不依赖任何前置库。
