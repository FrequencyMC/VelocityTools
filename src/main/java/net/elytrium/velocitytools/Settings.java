/*
 * Copyright (C) 2021 - 2023 Elytrium
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.elytrium.velocitytools;

import java.util.List;
import net.elytrium.java.commons.config.YamlConfig;
import net.elytrium.java.commons.mc.serialization.Serializers;

public class Settings extends YamlConfig {

  @Ignore
  public static final Settings IMP = new Settings();

  @Final
  public String VERSION = BuildConstants.VERSION;

  @Comment({
      "Available serializers:",
      "LEGACY_AMPERSAND - \"&c&lExample &c&9Text\".",
      "LEGACY_SECTION - \"§c§lExample §c§9Text\".",
      "MINIMESSAGE - \"<bold><red>Example</red> <blue>Text</blue></bold>\". (https://webui.adventure.kyori.net/)",
      "GSON - \"[{\"text\":\"Example\",\"bold\":true,\"color\":\"red\"},{\"text\":\" \",\"bold\":true},{\"text\":\"Text\",\"bold\":true,\"color\":\"blue\"}]\". (https://minecraft.tools/en/json_text.php/)",
      "GSON_COLOR_DOWNSAMPLING - Same as GSON, but uses downsampling."
  })
  public Serializers SERIALIZER = Serializers.LEGACY_AMPERSAND;

  @Create
  public COMMANDS COMMANDS;

  @Comment({
      "Don't use \\n, use {NL} for new line. Ampersand (&) color codes are supported too.\n",
      "",
      "Permissions:",
      "  │",
      "  └── Commands:",
      "      │",
      "      ├── /velocitytools reload",
      "      │   └── velocitytools.admin.reload",
      "      ├── /alert",
      "      │   └── velocitytools.command.alert",
      "      ├── /find",
      "      │   └── velocitytools.command.find",
      "      ├── /send",
      "      │   └── velocitytools.command.send",
      "      └── /hub",
      "          ├── velocitytools.command.hub",
      "          └── velocitytools.command.hub.bypass.<servername> (disabled-servers bypass permission)",
      ""
  })
  public static class COMMANDS {

    @Create
    public ALERT ALERT;
    @Create
    public FIND FIND;
    @Create
    public SEND SEND;
    @Create
    public HUB HUB;

    public static class ALERT {

      public boolean ENABLED = true;
      public String PREFIX = "&8[&4Alert&8] &r{0}";
      public String MESSAGE_NEEDED = "&cYou must supply the message.";
      public String EMPTY_PROXY = "&cNo one is connected to this proxy!";
    }

    public static class FIND {

      public boolean ENABLED = true;
      public String USERNAME_NEEDED = "&cYou must supply the username.";
      public String PLAYER_ONLINE_AT = "&6{0} &fis online at &6{1}";
      public String PLAYER_NOT_ONLINE = "&6{0} &fis not online.";
    }

    public static class SEND {

      public boolean ENABLED = true;
      public String CONSOLE = "CONSOLE";
      public String NOT_ENOUGH_ARGUMENTS = "&fNot enough arguments. Usage: &6/send <server|player|all|current> <target>";
      @Comment("Set to \"\" to disable.")
      public String YOU_GOT_SUMMONED = "&fSummoned to &6{0} &fby &6{1}";
      public String PLAYER_NOT_ONLINE = "&6{0} &fis not online.";
      public String CALLBACK = "&aAttempting to send {0} players to {1}";
    }

    public static class HUB {

      public boolean ENABLED = true;
      public List<String> SERVERS = List.of("hub-1", "hub-2");
      @Comment("Set to \"\" to disable.")
      public String YOU_GOT_MOVED = "&aYou have been moved to a hub!";
      public String DISABLED_SERVER = "&cYou cannot use this command here.";
      public List<String> DISABLED_SERVERS = List.of("foo", "bar");
      public List<String> ALIASES = List.of("hub", "lobby");
    }
  }
}
