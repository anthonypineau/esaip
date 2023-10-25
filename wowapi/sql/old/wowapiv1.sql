-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 28 mai 2021 à 15:44
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `wowapi`
--

-- --------------------------------------------------------

--
-- Structure de la table `classes`
--

DROP TABLE IF EXISTS `classes`;
CREATE TABLE IF NOT EXISTS `classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `powerType` varchar(45) COLLATE utf8_bin NOT NULL,
  `media` varchar(250) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `classes`
--

INSERT INTO `classes` (`id`, `name`, `powerType`, `media`) VALUES
(1, 'Warrior', 'Rage', 'https://render-us.worldofwarcraft.com/icons/56/classicon_warrior.jpg'),
(2, 'Paladin', 'Mana', 'https://render-us.worldofwarcraft.com/icons/56/classicon_paladin.jpg'),
(3, 'Hunter', 'Focus', 'https://render-us.worldofwarcraft.com/icons/56/classicon_hunter.jpg'),
(4, 'Rogue', 'Energy', 'https://render-us.worldofwarcraft.com/icons/56/classicon_rogue.jpg'),
(5, 'Priest', 'Mana', 'https://render-us.worldofwarcraft.com/icons/56/classicon_priest.jpg'),
(6, 'Death Knight', 'Runic Power', 'https://render-us.worldofwarcraft.com/icons/56/spell_deathknight_classicon.jpg'),
(7, 'Shaman', 'Mana', 'https://render-us.worldofwarcraft.com/icons/56/classicon_shaman.jpg'),
(8, 'Mage', 'Mana', 'https://render-us.worldofwarcraft.com/icons/56/classicon_mage.jpg'),
(9, 'Warlock', 'Mana', 'https://render-us.worldofwarcraft.com/icons/56/classicon_warlock.jpg'),
(10, 'Monk', 'Energy', 'https://render-us.worldofwarcraft.com/icons/56/classicon_monk.jpg'),
(11, 'Druid', 'Mana', 'https://render-us.worldofwarcraft.com/icons/56/classicon_druid.jpg'),
(12, 'Demon Hunter', 'Fury', 'https://render-us.worldofwarcraft.com/icons/56/achievement_boss_illidan.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `itemclasses`
--

DROP TABLE IF EXISTS `itemclasses`;
CREATE TABLE IF NOT EXISTS `itemclasses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `itemclasses`
--

INSERT INTO `itemclasses` (`id`, `name`) VALUES
(1, 'Axe'),
(2, 'Bow'),
(3, 'Gun'),
(4, 'Mace'),
(5, 'Polearm'),
(6, 'Sword'),
(7, 'Warglaives'),
(8, 'Staff'),
(9, 'Bear Claws'),
(10, 'Cat Claws'),
(11, 'Fist Weapon'),
(12, 'Miscellaneous'),
(13, 'Dagger'),
(14, 'Thrown'),
(15, 'Spear'),
(16, 'Crossbow'),
(17, 'Wand'),
(18, 'Fishing Pole'),
(19, 'Miscellaneous'),
(20, 'Cloth'),
(21, 'Leather'),
(22, 'Mail'),
(23, 'Plate'),
(24, 'Cosmetic'),
(25, 'Shield'),
(26, 'Reagent'),
(27, 'Keystone'),
(28, 'Context Token'),
(29, 'Arrow'),
(30, 'Bullet'),
(31, 'Quiver'),
(32, 'Ammo Pouch'),
(38, 'testitemcalss2');

-- --------------------------------------------------------

--
-- Structure de la table `items`
--

DROP TABLE IF EXISTS `items`;
CREATE TABLE IF NOT EXISTS `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `quality` varchar(45) COLLATE utf8_bin NOT NULL,
  `level` int(11) NOT NULL,
  `requiredLevel` int(11) NOT NULL,
  `purchasePrice` int(11) NOT NULL,
  `sellPrice` int(11) NOT NULL,
  `isEquippable` bit(1) NOT NULL,
  `isStackable` bit(1) NOT NULL,
  `media` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `itemClass` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1_Items` (`itemClass`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `items`
--

INSERT INTO `items` (`id`, `name`, `quality`, `level`, `requiredLevel`, `purchasePrice`, `sellPrice`, `isEquippable`, `isStackable`, `media`, `itemClass`) VALUES
(1, 'Tunnel Pick', 'Uncommon', 18, 11, 62055, 12411, b'1', b'1', 'https://render-us.worldofwarcraft.com/icons/56/inv_pick_02.jpg', 1),
(2, 'Lumberjack Axe', 'Common', 4, 2, 567, 113, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_axe_01.jpg', 1),
(3, 'Kobold Excavation Pick', 'Common', 3, 2, 279, 55, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_pick_02.jpg', 1),
(4, 'Forester\'s Axe', 'Uncommon', 14, 9, 16032, 3206, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_axe_14.jpg', 1),
(5, 'Axe of the Deep Woods', 'Epic', 28, 22, 92260, 18452, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_axe_07.jpg', 1),
(6, 'Worn Shortbow', 'Common', 1, 1, 49, 9, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_bow_05.jpg', 2),
(7, 'Polished Shortbow', 'Common', 2, 1, 100, 20, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_bow_02.jpg', 2),
(8, 'Hornwood Recurve Bow', 'Common', 4, 2, 475, 95, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_bow_11.jpg', 2),
(9, 'Cracked Shortbow', 'Poor', 4, 2, 327, 65, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_bow_05.jpg', 2),
(10, 'Feeble Shortbow', 'Poor', 6, 4, 1226, 245, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_bow_05.jpg', 2),
(11, 'Dirty Blunderbuss', 'Poor', 11, 6, 3215, 643, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_rifle_01.jpg', 3),
(12, 'Oiled Blunderbuss', 'Poor', 18, 11, 24447, 4889, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_rifle_01.jpg', 3),
(13, 'Rust-Covered Blunderbuss', 'Poor', 3, 2, 234, 46, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_rifle_03.jpg', 3),
(14, 'Large Bore Blunderbuss', 'Common', 13, 8, 8170, 1634, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_rifle_03.jpg', 3),
(15, 'BKP 2700 \"Enforcer\"', 'Common', 16, 10, 23056, 4611, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_rifle_03.jpg', 3),
(16, 'Coldridge Hammer', 'Uncommon', 6, 0, 2334, 466, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_hammer_15.jpg', 4),
(17, 'Beatstick', 'Common', 4, 2, 499, 99, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_mace_02.jpg', 4),
(18, 'Black Malice', 'Rare', 13, 8, 10467, 2093, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_mace_01.jpg', 4),
(19, 'Oak Mallet', 'Uncommon', 13, 8, 13466, 2693, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_hammer_15.jpg', 4),
(20, 'Battering Hammer', 'Uncommon', 14, 9, 21760, 4352, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_hammer_16.jpg', 4),
(21, 'Armor Piercer', 'Rare', 20, 13, 391151, 78230, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_spear_05.jpg', 5),
(22, 'Headspike', 'Rare', 28, 21, 4617560, 923512, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_spear_06.jpg', 5),
(23, 'Smoldering Claw', 'Rare', 28, 21, 93351, 18670, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_halberd_04.jpg', 5),
(24, 'Blackhand Doomsaw', 'Rare', 29, 23, 108289, 21657, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_halberd_04.jpg', 5),
(25, 'Darkspear', 'Rare', 29, 23, 105595, 21119, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_spear_08.jpg', 5),
(26, 'Coral Claymore', 'Uncommon', 8, 5, 4398, 879, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_sword_38.jpg', 6),
(27, 'Viking Sword', 'Uncommon', 18, 11, 58804, 11760, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_sword_33.jpg', 6),
(28, 'Short Bastard Sword', 'Uncommon', 6, 3, 2479, 495, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_sword_40.jpg', 6),
(29, 'Stonecutter Claymore', 'Uncommon', 20, 13, 352428, 70485, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_sword_47.jpg', 6),
(30, 'Cavalier Two-Hander', 'Uncommon', 18, 11, 54023, 10804, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_sword_20.jpg', 6),
(31, 'The All-Seeing Eye', 'Epic', 85, 50, 5409185, 1081837, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_glaive_1h_nzothraid_d_01.jpg', 7),
(32, 'Corrupted Gladiator\'s Warglaive', 'Epic', 60, 50, 5364208, 1072841, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_glaive_1h_nzothraid_d_01.jpg', 7),
(33, 'Corrupted Aspirant\'s Warglaive', 'Epic', 60, 50, 5051683, 1010336, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_glaive_1h_nzothraid_d_01.jpg', 7),
(34, 'Wingdancer\'s Warglaive', 'Epic', 174, 50, 5670756, 1134151, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_glaive_1h_revendreth_d_01.jpg', 7),
(35, 'Crimson Dredwing Glaive', 'Epic', 1, 50, 154, 30, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_glaive_1h_revendreth_d_01.jpg', 7),
(36, 'Gnarled Staff', 'Common', 12, 7, 6376, 1275, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_staff_10.jpg', 8),
(37, 'Cryptbone Staff', 'Uncommon', 16, 10, 35513, 7102, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_staff_26.jpg', 8),
(38, 'Staff of Westfall', 'Rare', 15, 0, 18197, 3639, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_staff_10.jpg', 8),
(39, 'Dwarven Magestaff', 'Uncommon', 17, 10, 47310, 9462, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_staff_04.jpg', 8),
(40, 'Frostbit Staff', 'Common', 5, 3, 931, 186, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_staff_15.jpg', 8),
(41, 'Hurd Smasher', 'Rare', 29, 23, 85742, 17148, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_gauntlets_11.jpg', 11),
(42, 'Blood Talon', 'Rare', 29, 23, 82320, 16464, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_shortblade_27.jpg', 11),
(43, 'Heavy Iron Knuckles', 'Uncommon', 19, 12, 17136, 3427, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_gauntlets_04.jpg', 11),
(44, 'Ravager Claws', 'Uncommon', 29, 24, 3169568, 633913, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/spell_nature_stoneclawtotem.jpg', 11),
(45, 'Vampiric Handscythes', 'Uncommon', 30, 24, 3144749, 628949, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_misc_monsterclaw_03.jpg', 11),
(46, 'Twain Random Sword', 'Poor', 12, 7, 3474, 694, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_sword_03.jpg', 12),
(47, 'Arclight Spanner', 'Common', 5, 0, 947, 189, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_misc_wrench_01.jpg', 12),
(48, 'Skinning Knife', 'Common', 2, 1, 124, 24, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_shortblade_01.jpg', 12),
(49, 'Twain Random Sword FOO', 'Poor', 12, 7, 3462, 692, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_sword_03.jpg', 12),
(50, 'Spare Hand', 'Poor', 1, 27, 12, 2, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_gauntlets_09.jpg', 12),
(51, 'Claw of the Shadowmancer', 'Rare', 19, 12, 20302, 4060, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_shortblade_16.jpg', 13),
(52, 'Thornblade', 'Uncommon', 12, 0, 7049, 1409, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_shortblade_15.jpg', 13),
(53, 'Prison Shank', 'Rare', 16, 10, 17761, 3552, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_shortblade_18.jpg', 13),
(54, 'Hook Dagger', 'Uncommon', 12, 7, 8019, 1603, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_shortblade_01.jpg', 13),
(55, 'Sacrificial Kris', 'Uncommon', 24, 17, 1159774, 231954, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_shortblade_03.jpg', 13),
(56, 'Serpentshrine Shuriken', 'Poor', 32, 27, 145472, 36368, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_throwingknife_06.jpg', 14),
(57, 'Mark V\'s Throwing Star', 'Poor', 31, 0, 138516, 34629, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/ability_upgrademoonglaive.jpg', 14),
(58, 'The Sharp Cookie', 'Poor', 31, 26, 22142, 5535, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/ability_upgrademoonglaive.jpg', 14),
(59, 'Avenging Blades', 'Poor', 31, 26, 22142, 5535, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/ability_upgrademoonglaive.jpg', 14),
(60, 'Thorium Flight Blade', 'Poor', 26, 0, 103656, 25914, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_throwingaxe_06.jpg', 14),
(61, 'Steelarrow Crossbow', 'Uncommon', 17, 10, 45493, 9098, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_crossbow_01.jpg', 16),
(62, 'Blackcrow', 'Rare', 29, 23, 4572047, 914409, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_crossbow_04.jpg', 16),
(63, 'Skull Splitting Crossbow', 'Rare', 25, 19, 3303847, 660769, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_crossbow_04.jpg', 16),
(64, 'Heartseeking Crossbow', 'Rare', 28, 22, 88671, 17734, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_crossbow_04.jpg', 16),
(65, 'Crystalpine Stinger', 'Rare', 19, 12, 117477, 23495, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_crossbow_04.jpg', 16),
(66, 'Spellcrafter Wand', 'Uncommon', 16, 0, 20010, 4002, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_wand_05.jpg', 17),
(67, 'Fizzle\'s Zippy Lighter', 'Uncommon', 21, 0, 59900, 11980, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_wand_11.jpg', 17),
(68, 'Eyepoker', 'Uncommon', 21, 0, 383940, 76788, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_wand_03.jpg', 17),
(69, 'Dancing Flame', 'Rare', 22, 0, 85079, 17015, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_wand_04.jpg', 17),
(70, 'Gravestone Scepter', 'Rare', 18, 0, 29461, 5892, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_wand_04.jpg', 17),
(71, 'Darkwood Fishing Pole', 'Common', 12, 7, 4449, 889, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_fishingpole_02.jpg', 18),
(72, 'Strong Fishing Pole', 'Common', 5, 3, 1185, 237, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_fishingpole_02.jpg', 18),
(73, 'Big Iron Fishing Pole', 'Common', 18, 11, 10665, 2133, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_fishingpole_01.jpg', 18),
(74, 'Fishing Pole', 'Common', 1, 0, 1, 1, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_fishingpole_02.jpg', 18),
(75, 'Blump Family Fishing Pole', 'Common', 5, 0, 1234, 246, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_fishingpole_02.jpg', 18),
(76, 'Recruit\'s Shirt', 'Uncommon', 1, 0, 1, 1, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_shirt_05.jpg', 19),
(77, 'Squire\'s Shirt', 'Uncommon', 1, 0, 1, 1, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_shirt_01.jpg', 19),
(78, 'Footpad\'s Shirt', 'Uncommon', 1, 0, 1, 1, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_shirt_05.jpg', 19),
(79, 'Neophyte\'s Shirt', 'Uncommon', 1, 0, 1, 1, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_shirt_01.jpg', 19),
(80, 'Trapper\'s Shirt', 'Uncommon', 1, 0, 1, 1, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_shirt_07.jpg', 19),
(81, 'Dwarven Cloth Britches', 'Common', 2, 0, 41, 8, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_pants_cloth_41v4.jpg', 20),
(82, 'Soft Fur-Lined Shoes', 'Common', 2, 0, 63, 12, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_09.jpg', 20),
(83, 'Acolyte\'s Robe', 'Common', 1, 1, 13, 2, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_chest_cloth_24.jpg', 20),
(84, 'Acolyte\'s Shoes', 'Common', 1, 0, 10, 2, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_01.jpg', 20),
(85, 'Apprentice\'s Robe', 'Common', 1, 1, 13, 2, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_chest_cloth_23.jpg', 20),
(86, 'Dirty Leather Vest', 'Common', 2, 1, 89, 17, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_shirt_07.jpg', 21),
(87, 'Thug Pants', 'Common', 1, 1, 12, 2, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_pants_02.jpg', 21),
(88, 'Thug Boots', 'Common', 1, 0, 9, 1, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_05.jpg', 21),
(89, 'Layered Tunic', 'Common', 2, 0, 85, 17, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_chest_leather_09.jpg', 21),
(90, 'Dwarven Leather Pants', 'Common', 2, 0, 37, 7, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_pants_leather_38v2.jpg', 21),
(91, 'Scalemail Pants', 'Common', 14, 8, 5435, 1087, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_pants_03.jpg', 22),
(92, 'Scalemail Boots', 'Common', 14, 8, 4084, 816, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_01.jpg', 22),
(93, 'Scalemail Vest', 'Common', 14, 8, 5985, 1197, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_chest_chain_09.jpg', 22),
(94, 'Scalemail Gloves', 'Common', 14, 8, 2733, 546, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_gauntlets_04.jpg', 22),
(95, 'Silver Defias Belt', 'Uncommon', 8, 5, 1191, 238, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_belt_03.jpg', 22),
(96, 'Recruit\'s Pants', 'Common', 1, 1, 13, 2, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_pants_02.jpg', 23),
(97, 'Recruit\'s Boots', 'Common', 1, 0, 9, 1, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_06.jpg', 23),
(98, 'Squire\'s Boots', 'Common', 1, 0, 9, 1, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_01.jpg', 23),
(99, 'Squire\'s Pants', 'Common', 1, 1, 12, 2, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_pants_02.jpg', 23),
(100, 'Brawler\'s Boots', 'Common', 1, 0, 9, 1, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_04.jpg', 23),
(101, 'Bloodsail Admiral\'s Hat', 'Uncommon', 29, 0, 28991, 5798, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_helmet_66.jpg', 24),
(102, 'Hallowed Helm', 'Rare', 1, 0, 14, 2, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_misc_bag_28_halloween.jpg', 24),
(103, 'Pilgrim\'s Dress', 'Uncommon', 1, 0, 1, 1, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_chest_cloth_51.jpg', 24),
(104, 'Pilgrim\'s Boots', 'Uncommon', 1, 0, 1, 1, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_cloth_17.jpg', 24),
(105, 'Spring Robes', 'Uncommon', 1, 0, 1, 1, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_chest_cloth_13.jpg', 24),
(106, 'Skullflame Shield', 'Epic', 29, 23, 74884, 14976, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_shield_01.jpg', 25),
(107, 'Small Targe', 'Common', 5, 3, 484, 96, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_shield_09.jpg', 25),
(108, 'Blackskull Shield', 'Epic', 24, 18, 2060899, 412179, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/spell_shadow_grimward.jpg', 25),
(109, 'Dull Heater Shield', 'Common', 5, 3, 473, 94, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_shield_04.jpg', 25),
(110, 'Wall Shield', 'Common', 10, 6, 1839, 367, b'1', b'0', 'https://render-us.worldofwarcraft.com/icons/56/inv_shield_03.jpg', 25);

-- --------------------------------------------------------

--
-- Structure de la table `mounts`
--

DROP TABLE IF EXISTS `mounts`;
CREATE TABLE IF NOT EXISTS `mounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `description` text COLLATE utf8_bin NOT NULL,
  `source` varchar(45) COLLATE utf8_bin NOT NULL,
  `faction` varchar(45) COLLATE utf8_bin NOT NULL,
  `media` varchar(250) COLLATE utf8_bin NOT NULL,
  `player` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1_Mounts` (`player`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `mounts`
--

INSERT INTO `mounts` (`id`, `name`, `description`, `source`, `faction`, `media`, `player`) VALUES
(1, 'Armored Brown Bear', 'Your cries of \"For the Horde!\" will be much more menacing from atop a heavily armored bear that\'s barreling down on Alliance at top speed.', 'Vendor', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27821.jpg', 1),
(2, 'Armored Brown Bear', 'This ursine mount\'s swiftness is enhanced by its heavy armor, imbued with magic from Kirin Tor magi.', 'Vendor', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27820.jpg', 1),
(3, 'Black War Bear', 'This formidable mount is bought with the blood of Alliance leaders, but you would have killed them for free.', 'Achievement', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27819.jpg', 2),
(4, 'Black War Bear', 'Killing leaders of the Horde is its own reward, but this intimidating beast is an excellent memento.', 'Achievement', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27818.jpg', 1),
(5, 'Mekgineer\'s Chopper', 'Sporting superb engineering craftsmanship, this chopper is one of the few personal vehicles on Azeroth built for two.', 'Profession', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-25870.jpg', 2),
(6, 'Armored Snowy Gryphon', 'The nice thing is that when they attack, blood almost never stains their feathers. -Mei Francis', 'Vendor', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27913.jpg', 1),
(7, 'Armored Blue Wind Rider', 'They are not native to Northrend, but they have adapted extremely well to the cold. -Mei Francis', 'Vendor', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27914.jpg', 2),
(8, 'Traveler\'s Tundra Mammoth', 'Ancient manuscripts depict merchants traveling across the frozen tundra with these colossal beasts, leading scholars to believe they are the first domesticated mounts.', 'Vendor', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27237.jpg', 1),
(9, 'Traveler\'s Tundra Mammoth', 'Riders and merchants alike trust only this strong and steadfast mammoth to carry them safely across the frigid tundra.', 'Vendor', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27238.jpg', 1),
(10, 'Grand Black War Mammoth', 'Once used by giants to carry heavily armored warriors, these grand war mammoths can easily bear more than one rider of less-than-giant stature.', 'Drop', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27241.jpg', 1),
(11, 'Grand Black War Mammoth', 'Rarely, a war mammoth is born stronger and more tractable than its peers. These mammoths are dubbed \"grand\" and carry more than one rider to the front lines.', 'Drop', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27240.jpg', 2),
(12, 'Grand Ice Mammoth', 'At home on the narrow, icy trails of the Storm Peaks, these mammoths are the only mounts the ice giants trust to carry large loads and multiple riders.', 'Vendor', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27239.jpg', 2),
(13, 'Grand Ice Mammoth', 'Due to high winds and avalanches, the Sons of Hodir recommend bringing at least two companions if venturing into the dangerous Storm Peaks.', 'Vendor', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27242.jpg', 2),
(14, 'Blue Dragonhawk', 'Dragonhawks are among the deadliest known predators. They are able to spot prey from great distances, rush in, and rend with both talon and beak.', 'Achievement', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27525.jpg', 2),
(15, 'Red Dragonhawk', 'Dragonhawks are among the deadliest known predators. They are able to spot prey from great distances, rush in, and rend with both talon and beak.', 'Achievement', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-28402.jpg', 2),
(16, 'Stormwind Steed', 'Although raised in Stormwind, this breed showed unusual aptitude for the cold weather in Icecrown.', 'Vendor', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-28912.jpg', 2),
(17, 'Darkspear Raptor', 'This raptor breed was originally cultivated by the Drakkari, but has since been adopted by the Darkspear tribe in Northrend.', 'Vendor', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29261.jpg', 2),
(18, 'Darnassian Nightsaber', 'When a night elf\'s mount perishes, it is custom for the rider to keep one of the saber\'s great fangs as a token of remembrance.', 'Vendor', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29256.jpg', 2),
(19, 'Ironforge Ram', 'Wildhammer Fact Checker claims that this specific breed was the first type of ram domesticated by the dwarves when they settled in Dun Morogh.', 'Vendor', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29258.jpg', 1),
(20, 'Gnomeregan Mechanostrider', 'When you absolutely, positively have to be there right now. -Rillie Spindlenut', 'Vendor', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-28571.jpg', 1),
(21, 'Exodar Elekk', 'Exodar Elekk are elekk which have been anointed by O\'ros, the Naaru who journeyed from Draenor to Azeroth with Velen and his followers.', 'Vendor', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29255.jpg', 1),
(22, 'Orgrimmar Wolf', 'For a brief time, many orcs would declare themselves \"hungry like an Orgrimmar wolf.\" Some blood elves still use the phrase ironically.', 'Vendor', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29260.jpg', 1),
(23, 'Forsaken Warhorse', 'When fallen heroes are raised into undeath, so too are their horses.', 'Vendor', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29257.jpg', 1),
(24, 'Thunder Bluff Kodo', 'An alpha member of the herd, this kodo is a prime example of the mighty creatures which thunder through Mulgore.', 'Vendor', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29259.jpg', 1),
(25, 'Silvermoon Hawkstrider', 'So I says I feel like chicken tonight. Next thing, I am flat out and that thing is trying to see what my eyes taste like. -Bozzle Blastbolt', 'Vendor', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29262.jpg', 1),
(26, 'White Kodo', '\"The kodo is the backbone of our people. Wherever the Horde goes - in trade, settlement, or war - there you will find the mighty kodo.\" - Harb Clawhoof', 'Vendor', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-12241.jpg', 1),
(27, 'Black Wolf', 'Can howl loudly enough to be heard for miles.', 'Vendor', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-207.jpg', 1),
(28, 'Venomhide Ravasaur', 'You raised this thing from a scaly pup - Mor\'vek would be so proud. But he\'d still make you clean up the mess it made in the corner.', 'Quest', 'Horde', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29102.jpg', 2),
(29, 'Swift Moonsaber', 'Many night elves revere these proud mounts because their shimmering coats symbolize the goddess of the moon, Elune.', 'Vendor', 'Alliance', 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-14333.jpg', 2);

-- --------------------------------------------------------

--
-- Structure de la table `players`
--

DROP TABLE IF EXISTS `players`;
CREATE TABLE IF NOT EXISTS `players` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_bin NOT NULL,
  `isConnected` bit(1) NOT NULL,
  `level` int(11) NOT NULL,
  `icon` varchar(250) COLLATE utf8_bin NOT NULL,
  `class` int(11) NOT NULL,
  `race` int(11) NOT NULL,
  `profession` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1_Players` (`class`),
  KEY `fk2_Players` (`race`),
  KEY `fk3_Players` (`profession`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `players`
--

INSERT INTO `players` (`id`, `username`, `isConnected`, `level`, `icon`, `class`, `race`, `profession`) VALUES
(1, 'Geekixel', b'1', 30, 'https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png', 1, 1, 1),
(2, 'Oxii', b'0', 60, 'https://cdn.pixabay.com/photo/2014/04/03/10/32/businessman-310819_960_720.png', 4, 8, 2);

-- --------------------------------------------------------

--
-- Structure de la table `playersitems`
--

DROP TABLE IF EXISTS `playersitems`;
CREATE TABLE IF NOT EXISTS `playersitems` (
  `item` int(11) NOT NULL,
  `player` int(11) NOT NULL,
  `numberInInventory` int(11) NOT NULL,
  PRIMARY KEY (`item`,`player`),
  KEY `fk2_PlayersItems` (`player`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `playersitems`
--

INSERT INTO `playersitems` (`item`, `player`, `numberInInventory`) VALUES
(1, 1, 1),
(2, 1, 1),
(2, 2, 10),
(7, 2, 2),
(8, 1, 1),
(11, 2, 1),
(15, 2, 1),
(20, 1, 3),
(34, 1, 5);

-- --------------------------------------------------------

--
-- Structure de la table `professions`
--

DROP TABLE IF EXISTS `professions`;
CREATE TABLE IF NOT EXISTS `professions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `description` text COLLATE utf8_bin NOT NULL,
  `type` varchar(45) COLLATE utf8_bin NOT NULL,
  `media` varchar(250) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `professions`
--

INSERT INTO `professions` (`id`, `name`, `description`, `type`, `media`) VALUES
(1, 'Blacksmithing', 'Higher smithing skill allows you to learn higher level smithing plans. Blacksmithing plans can be found on trainers around the world as well as from quests and monsters.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/trade_blacksmithing.jpg'),
(2, 'Skinning', 'Higher skinning skill allows you to skin hides from higher level monsters around the world.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/inv_misc_pelt_wolf_01.jpg'),
(3, 'Jewelcrafting', 'Higher jewelcrafting skill allows you to learn higher level jewelcrafting plans. Jewelcrafting plans can be found on trainers around the world as well as from quests and monsters.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/inv_misc_gem_01.jpg'),
(4, 'Leatherworking', 'Higher leatherworking skill allows you to learn higher level leatherworking patterns. Leatherworking patterns can be found on trainers around the world as well as from quests and monsters.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/trade_leatherworking.jpg'),
(5, 'Alchemy', 'Higher alchemy skill allows you to learn higher level alchemy recipes. Alchemy recipes can be found on trainers around the world as well as from quests and monsters.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/trade_alchemy.jpg'),
(6, 'Inscription', 'Higher inscription skill allows you to learn more advanced inscriptions. Inscriptions are acquired from learned scribes throughout the world.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/inv_inscription_tradeskill01.jpg'),
(7, 'Archaeology', 'Higher archaeology skill allows you to complete more valuable artifacts and collect more artifact fragments. Fragments can be found in ruins and dig sites.', 'Secondary', 'https://render-us.worldofwarcraft.com/icons/56/trade_archaeology.jpg'),
(8, 'Herbalism', 'Higher herbalism skill allows you to harvest more difficult herbs around the world. If you cannot harvest a specific herb, then increase your skill by harvesting easier to gather herbs in lower level areas.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/trade_herbalism.jpg'),
(9, 'Mining', 'Higher mining skill allows you to harvest more difficult minerals nodes around the world. If you cannot harvest a specific mineral, then increase your skill by mining easier to mine minerals in lower level areas.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/inv_pick_02.jpg'),
(10, 'Tailoring', 'Higher tailoring skill allows you to learn higher level tailoring patterns. Tailoring patterns can be found on trainers around the world as well as from quests and monsters.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/trade_tailoring.jpg'),
(11, 'Engineering', 'Higher engineering skill allows you to learn higher level engineering schematics. Schematics can be found on trainers around the world as well as from quests and monsters.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/trade_engineering.jpg'),
(12, 'Soul Cyphering', 'New soul cyphers can be found within the Maw.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/sha_spell_warlock_demonsoul.jpg'),
(13, 'Enchanting', 'Higher enchanting skill allows you to learn more powerful formulae. Formulae can be found on trainers around the world as well as from quests and monsters.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/trade_engraving.jpg'),
(14, 'Cooking', 'Higher cooking skill allows you to learn higher level cooking recipes. Recipes can be found on trainers around the world as well as from quests and as drops from monsters.', 'Secondary', 'https://render-us.worldofwarcraft.com/icons/56/inv_misc_food_15.jpg'),
(15, 'Abominable Stitching', 'Parts can be found on monsters in the Shadowlands, and from quests.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/sanctum_features_buildabom.jpg'),
(16, 'Fishing', 'Higher fishing skill increases your chance of catching fish in bodies of water around the world. If you are having trouble catching fish in a given area, move to a lower level area or purchase a fishing lure and try again.', 'Secondary', 'https://render-us.worldofwarcraft.com/icons/56/trade_fishing.jpg'),
(17, 'Ascension Crafting', 'Recipes can be obtained through the Path of Ascension feature.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/inv_mace_1h_bastionquest_b_01.jpg'),
(18, 'Stygia Crafting', 'Must be in possession of Soulforger\'s Tools.', 'Primary', 'https://render-us.worldofwarcraft.com/icons/56/inv_blacksmithing_815_khazgorianhammer.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `races`
--

DROP TABLE IF EXISTS `races`;
CREATE TABLE IF NOT EXISTS `races` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `faction` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `races`
--

INSERT INTO `races` (`id`, `name`, `faction`) VALUES
(1, 'Human', 'Alliance'),
(2, 'Orc', 'Horde'),
(3, 'Dwarf', 'Alliance'),
(4, 'Night Elf', 'Alliance'),
(5, 'Undead', 'Horde'),
(6, 'Tauren', 'Horde'),
(7, 'Gnome', 'Alliance'),
(8, 'Troll', 'Horde'),
(9, 'Goblin', 'Horde'),
(10, 'Blood Elf', 'Horde'),
(11, 'Draenei', 'Alliance'),
(12, 'Worgen', 'Alliance'),
(13, 'Pandaren', 'Neutral'),
(14, 'Pandaren', 'Horde'),
(15, 'Nightborne', 'Horde'),
(16, 'Highmountain Tauren', 'Horde'),
(17, 'Void Elf', 'Alliance'),
(18, 'Lightforged Draenei', 'Alliance'),
(19, 'Zandalari Troll', 'Horde'),
(20, 'Kul Tiran', 'Alliance'),
(21, 'Dark Iron Dwarf', 'Alliance'),
(22, 'Vulpera', 'Horde'),
(23, 'Mag\'har Orc', 'Horde'),
(24, 'Mechagnome', 'Alliance'),
(25, 'Pandaren', 'Alliance');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(150) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `fk1_Items` FOREIGN KEY (`itemClass`) REFERENCES `itemclasses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `mounts`
--
ALTER TABLE `mounts`
  ADD CONSTRAINT `fk1_Mounts` FOREIGN KEY (`player`) REFERENCES `players` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `players`
--
ALTER TABLE `players`
  ADD CONSTRAINT `fk1_Players` FOREIGN KEY (`class`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2_Players` FOREIGN KEY (`race`) REFERENCES `races` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk3_Players` FOREIGN KEY (`profession`) REFERENCES `professions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `playersitems`
--
ALTER TABLE `playersitems`
  ADD CONSTRAINT `fk1_PlayersItems` FOREIGN KEY (`item`) REFERENCES `items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2_PlayersItems` FOREIGN KEY (`player`) REFERENCES `players` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
