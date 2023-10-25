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
-- Base de données : `wowapiv2`
--

-- --------------------------------------------------------

--
-- Structure de la table `classes`
--

DROP TABLE IF EXISTS `classes`;
CREATE TABLE IF NOT EXISTS `classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `powerType` int(11) NOT NULL,
  `media` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1_Classes` (`media`),
  KEY `fk2_Classes` (`powerType`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `classes`
--

INSERT INTO `classes` (`id`, `name`, `powerType`, `media`) VALUES
(1, 'Warrior', 1, 128),
(2, 'Paladin', 2, 129),
(3, 'Hunter', 3, 130),
(4, 'Rogue', 4, 131),
(5, 'Priest', 2, 132),
(6, 'Death Knight', 5, 133),
(7, 'Shaman', 2, 134),
(8, 'Mage', 2, 135),
(9, 'Warlock', 2, 136),
(10, 'Monk', 4, 137),
(11, 'Druid', 2, 138),
(12, 'Demon Hunter', 6, 139);

-- --------------------------------------------------------

--
-- Structure de la table `factions`
--

DROP TABLE IF EXISTS `factions`;
CREATE TABLE IF NOT EXISTS `factions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `factions`
--

INSERT INTO `factions` (`id`, `name`) VALUES
(1, 'Horde'),
(2, 'Alliance');

-- --------------------------------------------------------

--
-- Structure de la table `itemclasses`
--

DROP TABLE IF EXISTS `itemclasses`;
CREATE TABLE IF NOT EXISTS `itemclasses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
(32, 'Ammo Pouch');

-- --------------------------------------------------------

--
-- Structure de la table `items`
--

DROP TABLE IF EXISTS `items`;
CREATE TABLE IF NOT EXISTS `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `quality` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `requiredLevel` int(11) NOT NULL,
  `purchasePrice` int(11) NOT NULL,
  `sellPrice` int(11) NOT NULL,
  `isEquippable` int(11) NOT NULL,
  `isStackable` int(11) NOT NULL,
  `media` int(11) NOT NULL,
  `itemClass` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1_Items` (`itemClass`),
  KEY `fk2_Items` (`media`),
  KEY `fk3_Items` (`quality`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `items`
--

INSERT INTO `items` (`id`, `name`, `quality`, `level`, `requiredLevel`, `purchasePrice`, `sellPrice`, `isEquippable`, `isStackable`, `media`, `itemClass`) VALUES
(1, 'Tunnel Pick', 1, 18, 11, 62055, 12411, 1, 1, 1, 1),
(2, 'Kobold Excavation Pick', 2, 3, 2, 279, 55, 1, 0, 1, 1),
(3, 'Lumberjack Axe', 2, 4, 2, 567, 113, 1, 0, 2, 1),
(4, 'Forester\'s Axe', 1, 14, 9, 16032, 3206, 1, 0, 3, 1),
(5, 'Axe of the Deep Woods', 3, 28, 22, 92260, 18452, 1, 0, 4, 1),
(6, 'Worn Shortbow', 2, 1, 1, 49, 9, 1, 0, 5, 2),
(7, 'Cracked Shortbow', 4, 4, 2, 327, 65, 1, 0, 5, 2),
(8, 'Feeble Shortbow', 4, 6, 4, 1226, 245, 1, 0, 5, 2),
(9, 'Polished Shortbow', 2, 2, 1, 100, 20, 1, 0, 6, 2),
(10, 'Hornwood Recurve Bow', 2, 4, 2, 475, 95, 1, 0, 7, 2),
(11, 'Dirty Blunderbuss', 4, 11, 6, 3215, 643, 1, 0, 8, 3),
(12, 'Oiled Blunderbuss', 4, 18, 11, 24447, 4889, 1, 0, 8, 3),
(13, 'Rust-Covered Blunderbuss', 4, 3, 2, 234, 46, 1, 0, 9, 3),
(14, 'Large Bore Blunderbuss', 2, 13, 8, 8170, 1634, 1, 0, 9, 3),
(15, 'BKP 2700 \"Enforcer\"', 2, 16, 10, 23056, 4611, 1, 0, 9, 3),
(16, 'Coldridge Hammer', 1, 6, 0, 2334, 466, 1, 0, 10, 4),
(17, 'Oak Mallet', 1, 13, 8, 13466, 2693, 1, 0, 10, 4),
(18, 'Beatstick', 2, 4, 2, 499, 99, 1, 0, 11, 4),
(19, 'Black Malice', 5, 13, 8, 10467, 2093, 1, 0, 12, 4),
(20, 'Battering Hammer', 1, 14, 9, 21760, 4352, 1, 0, 13, 4),
(21, 'Armor Piercer', 5, 20, 13, 391151, 78230, 1, 0, 14, 5),
(22, 'Headspike', 5, 28, 21, 4617560, 923512, 1, 0, 15, 5),
(23, 'Smoldering Claw', 5, 28, 21, 93351, 18670, 1, 0, 16, 5),
(24, 'Blackhand Doomsaw', 5, 29, 23, 108289, 21657, 1, 0, 16, 5),
(25, 'Darkspear', 5, 29, 23, 105595, 21119, 1, 0, 17, 5),
(26, 'Coral Claymore', 1, 8, 5, 4398, 879, 1, 0, 18, 6),
(27, 'Viking Sword', 1, 18, 11, 58804, 11760, 1, 0, 19, 6),
(28, 'Short Bastard Sword', 1, 6, 3, 2479, 495, 1, 0, 20, 6),
(29, 'Stonecutter Claymore', 1, 20, 13, 352428, 70485, 1, 0, 21, 6),
(30, 'Cavalier Two-Hander', 1, 18, 11, 54023, 10804, 1, 0, 22, 6),
(31, 'The All-Seeing Eye', 3, 85, 50, 5409185, 1081837, 1, 0, 23, 7),
(32, 'Corrupted Gladiator\'s Warglaive', 3, 60, 50, 5364208, 1072841, 1, 0, 23, 7),
(33, 'Corrupted Aspirant\'s Warglaive', 3, 60, 50, 5051683, 1010336, 1, 0, 23, 7),
(34, 'Wingdancer\'s Warglaive', 3, 174, 50, 5670756, 1134151, 1, 0, 24, 7),
(35, 'Crimson Dredwing Glaive', 3, 1, 50, 154, 30, 1, 0, 24, 7),
(36, 'Gnarled Staff', 2, 12, 7, 6376, 1275, 1, 0, 25, 8),
(37, 'Staff of Westfall', 5, 15, 0, 18197, 3639, 1, 0, 25, 8),
(38, 'Cryptbone Staff', 1, 16, 10, 35513, 7102, 1, 0, 26, 8),
(39, 'Dwarven Magestaff', 1, 17, 10, 47310, 9462, 1, 0, 27, 8),
(40, 'Frostbit Staff', 2, 5, 3, 931, 186, 1, 0, 28, 8),
(41, 'Hurd Smasher', 5, 29, 23, 85742, 17148, 1, 0, 29, 11),
(42, 'Blood Talon', 5, 29, 23, 82320, 16464, 1, 0, 30, 11),
(43, 'Heavy Iron Knuckles', 1, 19, 12, 17136, 3427, 1, 0, 31, 11),
(44, 'Scalemail Gloves', 2, 14, 8, 2733, 546, 1, 0, 31, 22),
(45, 'Ravager Claws', 1, 29, 24, 3169568, 633913, 1, 0, 32, 11),
(46, 'Vampiric Handscythes', 1, 30, 24, 3144749, 628949, 1, 0, 33, 11),
(47, 'Twain Random Sword', 4, 12, 7, 3474, 694, 1, 0, 34, 12),
(48, 'Twain Random Sword FOO', 4, 12, 7, 3462, 692, 1, 0, 34, 12),
(49, 'Arclight Spanner', 2, 5, 0, 947, 189, 1, 0, 35, 12),
(50, 'Skinning Knife', 2, 2, 1, 124, 24, 1, 0, 36, 12),
(51, 'Hook Dagger', 1, 12, 7, 8019, 1603, 1, 0, 36, 13),
(52, 'Spare Hand', 4, 1, 27, 12, 2, 1, 0, 37, 12),
(53, 'Claw of the Shadowmancer', 5, 19, 12, 20302, 4060, 1, 0, 38, 13),
(54, 'Thornblade', 1, 12, 0, 7049, 1409, 1, 0, 39, 13),
(55, 'Prison Shank', 5, 16, 10, 17761, 3552, 1, 0, 40, 13),
(56, 'Sacrificial Kris', 1, 24, 17, 1159774, 231954, 1, 0, 41, 13),
(57, 'Serpentshrine Shuriken', 4, 32, 27, 145472, 36368, 1, 0, 42, 14),
(58, 'Mark V\'s Throwing Star', 4, 31, 0, 138516, 34629, 1, 0, 43, 14),
(59, 'The Sharp Cookie', 4, 31, 26, 22142, 5535, 1, 0, 43, 14),
(60, 'Avenging Blades', 4, 31, 26, 22142, 5535, 1, 0, 43, 14),
(61, 'Thorium Flight Blade', 4, 26, 0, 103656, 25914, 1, 0, 44, 14),
(62, 'Steelarrow Crossbow', 1, 17, 10, 45493, 9098, 1, 0, 45, 16),
(63, 'Blackcrow', 5, 29, 23, 4572047, 914409, 1, 0, 46, 16),
(64, 'Skull Splitting Crossbow', 5, 25, 19, 3303847, 660769, 1, 0, 46, 16),
(65, 'Heartseeking Crossbow', 5, 28, 22, 88671, 17734, 1, 0, 46, 16),
(66, 'Crystalpine Stinger', 5, 19, 12, 117477, 23495, 1, 0, 46, 16),
(67, 'Spellcrafter Wand', 1, 16, 0, 20010, 4002, 1, 0, 47, 17),
(68, 'Fizzle\'s Zippy Lighter', 1, 21, 0, 59900, 11980, 1, 0, 48, 17),
(69, 'Eyepoker', 1, 21, 0, 383940, 76788, 1, 0, 49, 17),
(70, 'Dancing Flame', 5, 22, 0, 85079, 17015, 1, 0, 50, 17),
(71, 'Gravestone Scepter', 5, 18, 0, 29461, 5892, 1, 0, 50, 17),
(72, 'Darkwood Fishing Pole', 2, 12, 7, 4449, 889, 1, 0, 51, 18),
(73, 'Strong Fishing Pole', 2, 5, 3, 1185, 237, 1, 0, 51, 18),
(74, 'Fishing Pole', 2, 1, 0, 1, 1, 1, 0, 51, 18),
(75, 'Blump Family Fishing Pole', 2, 5, 0, 1234, 246, 1, 0, 51, 18),
(76, 'Big Iron Fishing Pole', 2, 18, 11, 10665, 2133, 1, 0, 52, 18),
(77, 'Recruit\'s Shirt', 1, 1, 0, 1, 1, 1, 0, 53, 19),
(78, 'Footpad\'s Shirt', 1, 1, 0, 1, 1, 1, 0, 53, 19),
(79, 'Squire\'s Shirt', 1, 1, 0, 1, 1, 1, 0, 54, 19),
(80, 'Neophyte\'s Shirt', 1, 1, 0, 1, 1, 1, 0, 54, 19),
(81, 'Trapper\'s Shirt', 1, 1, 0, 1, 1, 1, 0, 55, 19),
(82, 'Dirty Leather Vest', 2, 2, 1, 89, 17, 1, 0, 55, 21),
(83, 'Dwarven Cloth Britches', 2, 2, 0, 41, 8, 1, 0, 56, 20),
(84, 'Soft Fur-Lined Shoes', 2, 2, 0, 63, 12, 1, 0, 57, 20),
(85, 'Acolyte\'s Robe', 2, 1, 1, 13, 2, 1, 0, 58, 20),
(86, 'Acolyte\'s Shoes', 2, 1, 0, 10, 2, 1, 0, 59, 20),
(87, 'Scalemail Boots', 2, 14, 8, 4084, 816, 1, 0, 59, 22),
(88, 'Squire\'s Boots', 2, 1, 0, 9, 1, 1, 0, 59, 23),
(89, 'Apprentice\'s Robe', 2, 1, 1, 13, 2, 1, 0, 60, 20),
(90, 'Thug Pants', 2, 1, 1, 12, 2, 1, 0, 61, 21),
(91, 'Recruit\'s Pants', 2, 1, 1, 13, 2, 1, 0, 61, 23),
(92, 'Squire\'s Pants', 2, 1, 1, 12, 2, 1, 0, 61, 23),
(93, 'Thug Boots', 2, 1, 0, 9, 1, 1, 0, 62, 21),
(94, 'Layered Tunic', 2, 2, 0, 85, 17, 1, 0, 63, 21),
(95, 'Dwarven Leather Pants', 2, 2, 0, 37, 7, 1, 0, 64, 21),
(96, 'Scalemail Pants', 2, 14, 8, 5435, 1087, 1, 0, 65, 22),
(97, 'Scalemail Vest', 2, 14, 8, 5985, 1197, 1, 0, 66, 22),
(98, 'Silver Defias Belt', 1, 8, 5, 1191, 238, 1, 0, 67, 22),
(99, 'Recruit\'s Boots', 2, 1, 0, 9, 1, 1, 0, 68, 23),
(100, 'Brawler\'s Boots', 2, 1, 0, 9, 1, 1, 0, 69, 23),
(101, 'Bloodsail Admiral\'s Hat', 1, 29, 0, 28991, 5798, 1, 0, 70, 24),
(102, 'Hallowed Helm', 5, 1, 0, 14, 2, 1, 0, 71, 24),
(103, 'Pilgrim\'s Dress', 1, 1, 0, 1, 1, 1, 0, 72, 24),
(104, 'Pilgrim\'s Boots', 1, 1, 0, 1, 1, 1, 0, 73, 24),
(105, 'Spring Robes', 1, 1, 0, 1, 1, 1, 0, 74, 24),
(106, 'Skullflame Shield', 3, 29, 23, 74884, 14976, 1, 0, 75, 25),
(107, 'Small Targe', 2, 5, 3, 484, 96, 1, 0, 76, 25),
(108, 'Blackskull Shield', 3, 24, 18, 2060899, 412179, 1, 0, 77, 25),
(109, 'Dull Heater Shield', 2, 5, 3, 473, 94, 1, 0, 78, 25),
(110, 'Wall Shield', 2, 10, 6, 1839, 367, 1, 0, 79, 25),
(111, 'Tunnel Pick', 1, 18, 11, 62055, 12411, 1, 1, 185, 1),
(112, 'Kobold Excavation Pick', 2, 3, 2, 279, 55, 1, 0, 185, 1);

-- --------------------------------------------------------

--
-- Structure de la table `medias`
--

DROP TABLE IF EXISTS `medias`;
CREATE TABLE IF NOT EXISTS `medias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(250) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `medias`
--

INSERT INTO `medias` (`id`, `link`) VALUES
(1, 'https://render-us.worldofwarcraft.com/icons/56/inv_pick_02.jpg'),
(2, 'https://render-us.worldofwarcraft.com/icons/56/inv_axe_01.jpg'),
(3, 'https://render-us.worldofwarcraft.com/icons/56/inv_axe_14.jpg'),
(4, 'https://render-us.worldofwarcraft.com/icons/56/inv_axe_07.jpg'),
(5, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_bow_05.jpg'),
(6, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_bow_02.jpg'),
(7, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_bow_11.jpg'),
(8, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_rifle_01.jpg'),
(9, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_rifle_03.jpg'),
(10, 'https://render-us.worldofwarcraft.com/icons/56/inv_hammer_15.jpg'),
(11, 'https://render-us.worldofwarcraft.com/icons/56/inv_mace_02.jpg'),
(12, 'https://render-us.worldofwarcraft.com/icons/56/inv_mace_01.jpg'),
(13, 'https://render-us.worldofwarcraft.com/icons/56/inv_hammer_16.jpg'),
(14, 'https://render-us.worldofwarcraft.com/icons/56/inv_spear_05.jpg'),
(15, 'https://render-us.worldofwarcraft.com/icons/56/inv_spear_06.jpg'),
(16, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_halberd_04.jpg'),
(17, 'https://render-us.worldofwarcraft.com/icons/56/inv_spear_08.jpg'),
(18, 'https://render-us.worldofwarcraft.com/icons/56/inv_sword_38.jpg'),
(19, 'https://render-us.worldofwarcraft.com/icons/56/inv_sword_33.jpg'),
(20, 'https://render-us.worldofwarcraft.com/icons/56/inv_sword_40.jpg'),
(21, 'https://render-us.worldofwarcraft.com/icons/56/inv_sword_47.jpg'),
(22, 'https://render-us.worldofwarcraft.com/icons/56/inv_sword_20.jpg'),
(23, 'https://render-us.worldofwarcraft.com/icons/56/inv_glaive_1h_nzothraid_d_01.jpg'),
(24, 'https://render-us.worldofwarcraft.com/icons/56/inv_glaive_1h_revendreth_d_01.jpg'),
(25, 'https://render-us.worldofwarcraft.com/icons/56/inv_staff_10.jpg'),
(26, 'https://render-us.worldofwarcraft.com/icons/56/inv_staff_26.jpg'),
(27, 'https://render-us.worldofwarcraft.com/icons/56/inv_staff_04.jpg'),
(28, 'https://render-us.worldofwarcraft.com/icons/56/inv_staff_15.jpg'),
(29, 'https://render-us.worldofwarcraft.com/icons/56/inv_gauntlets_11.jpg'),
(30, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_shortblade_27.jpg'),
(31, 'https://render-us.worldofwarcraft.com/icons/56/inv_gauntlets_04.jpg'),
(32, 'https://render-us.worldofwarcraft.com/icons/56/spell_nature_stoneclawtotem.jpg'),
(33, 'https://render-us.worldofwarcraft.com/icons/56/inv_misc_monsterclaw_03.jpg'),
(34, 'https://render-us.worldofwarcraft.com/icons/56/inv_sword_03.jpg'),
(35, 'https://render-us.worldofwarcraft.com/icons/56/inv_misc_wrench_01.jpg'),
(36, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_shortblade_01.jpg'),
(37, 'https://render-us.worldofwarcraft.com/icons/56/inv_gauntlets_09.jpg'),
(38, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_shortblade_16.jpg'),
(39, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_shortblade_15.jpg'),
(40, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_shortblade_18.jpg'),
(41, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_shortblade_03.jpg'),
(42, 'https://render-us.worldofwarcraft.com/icons/56/inv_throwingknife_06.jpg'),
(43, 'https://render-us.worldofwarcraft.com/icons/56/ability_upgrademoonglaive.jpg'),
(44, 'https://render-us.worldofwarcraft.com/icons/56/inv_throwingaxe_06.jpg'),
(45, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_crossbow_01.jpg'),
(46, 'https://render-us.worldofwarcraft.com/icons/56/inv_weapon_crossbow_04.jpg'),
(47, 'https://render-us.worldofwarcraft.com/icons/56/inv_wand_05.jpg'),
(48, 'https://render-us.worldofwarcraft.com/icons/56/inv_wand_11.jpg'),
(49, 'https://render-us.worldofwarcraft.com/icons/56/inv_wand_03.jpg'),
(50, 'https://render-us.worldofwarcraft.com/icons/56/inv_wand_04.jpg'),
(51, 'https://render-us.worldofwarcraft.com/icons/56/inv_fishingpole_02.jpg'),
(52, 'https://render-us.worldofwarcraft.com/icons/56/inv_fishingpole_01.jpg'),
(53, 'https://render-us.worldofwarcraft.com/icons/56/inv_shirt_05.jpg'),
(54, 'https://render-us.worldofwarcraft.com/icons/56/inv_shirt_01.jpg'),
(55, 'https://render-us.worldofwarcraft.com/icons/56/inv_shirt_07.jpg'),
(56, 'https://render-us.worldofwarcraft.com/icons/56/inv_pants_cloth_41v4.jpg'),
(57, 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_09.jpg'),
(58, 'https://render-us.worldofwarcraft.com/icons/56/inv_chest_cloth_24.jpg'),
(59, 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_01.jpg'),
(60, 'https://render-us.worldofwarcraft.com/icons/56/inv_chest_cloth_23.jpg'),
(61, 'https://render-us.worldofwarcraft.com/icons/56/inv_pants_02.jpg'),
(62, 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_05.jpg'),
(63, 'https://render-us.worldofwarcraft.com/icons/56/inv_chest_leather_09.jpg'),
(64, 'https://render-us.worldofwarcraft.com/icons/56/inv_pants_leather_38v2.jpg'),
(65, 'https://render-us.worldofwarcraft.com/icons/56/inv_pants_03.jpg'),
(66, 'https://render-us.worldofwarcraft.com/icons/56/inv_chest_chain_09.jpg'),
(67, 'https://render-us.worldofwarcraft.com/icons/56/inv_belt_03.jpg'),
(68, 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_06.jpg'),
(69, 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_04.jpg'),
(70, 'https://render-us.worldofwarcraft.com/icons/56/inv_helmet_66.jpg'),
(71, 'https://render-us.worldofwarcraft.com/icons/56/inv_misc_bag_28_halloween.jpg'),
(72, 'https://render-us.worldofwarcraft.com/icons/56/inv_chest_cloth_51.jpg'),
(73, 'https://render-us.worldofwarcraft.com/icons/56/inv_boots_cloth_17.jpg'),
(74, 'https://render-us.worldofwarcraft.com/icons/56/inv_chest_cloth_13.jpg'),
(75, 'https://render-us.worldofwarcraft.com/icons/56/inv_shield_01.jpg'),
(76, 'https://render-us.worldofwarcraft.com/icons/56/inv_shield_09.jpg'),
(77, 'https://render-us.worldofwarcraft.com/icons/56/spell_shadow_grimward.jpg'),
(78, 'https://render-us.worldofwarcraft.com/icons/56/inv_shield_04.jpg'),
(79, 'https://render-us.worldofwarcraft.com/icons/56/inv_shield_03.jpg'),
(128, 'https://render-us.worldofwarcraft.com/icons/56/classicon_warrior.jpg'),
(129, 'https://render-us.worldofwarcraft.com/icons/56/classicon_paladin.jpg'),
(130, 'https://render-us.worldofwarcraft.com/icons/56/classicon_hunter.jpg'),
(131, 'https://render-us.worldofwarcraft.com/icons/56/classicon_rogue.jpg'),
(132, 'https://render-us.worldofwarcraft.com/icons/56/classicon_priest.jpg'),
(133, 'https://render-us.worldofwarcraft.com/icons/56/spell_deathknight_classicon.jpg'),
(134, 'https://render-us.worldofwarcraft.com/icons/56/classicon_shaman.jpg'),
(135, 'https://render-us.worldofwarcraft.com/icons/56/classicon_mage.jpg'),
(136, 'https://render-us.worldofwarcraft.com/icons/56/classicon_warlock.jpg'),
(137, 'https://render-us.worldofwarcraft.com/icons/56/classicon_monk.jpg'),
(138, 'https://render-us.worldofwarcraft.com/icons/56/classicon_druid.jpg'),
(139, 'https://render-us.worldofwarcraft.com/icons/56/achievement_boss_illidan.jpg'),
(143, 'https://cdn.pixabay.com/photo/2013/07/13/10/07/man-156584_960_720.png'),
(144, 'https://cdn.pixabay.com/photo/2014/04/03/10/32/businessman-310819_960_720.png'),
(146, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27821.jpg'),
(147, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27820.jpg'),
(148, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27819.jpg'),
(149, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27818.jpg'),
(150, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-25870.jpg'),
(151, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27913.jpg'),
(152, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27914.jpg'),
(153, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27237.jpg'),
(154, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27238.jpg'),
(155, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27241.jpg'),
(156, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27240.jpg'),
(157, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27239.jpg'),
(158, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27242.jpg'),
(159, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-27525.jpg'),
(160, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-28402.jpg'),
(161, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-28912.jpg'),
(162, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29261.jpg'),
(163, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29256.jpg'),
(164, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29258.jpg'),
(165, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-28571.jpg'),
(166, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29255.jpg'),
(167, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29260.jpg'),
(168, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29257.jpg'),
(169, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29259.jpg'),
(170, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29262.jpg'),
(171, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-12241.jpg'),
(172, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-207.jpg'),
(173, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-29102.jpg'),
(174, 'https://render-us.worldofwarcraft.com/npcs/zoom/creature-display-14333.jpg'),
(177, 'https://render-us.worldofwarcraft.com/icons/56/trade_blacksmithing.jpg'),
(178, 'https://render-us.worldofwarcraft.com/icons/56/inv_misc_pelt_wolf_01.jpg'),
(179, 'https://render-us.worldofwarcraft.com/icons/56/inv_misc_gem_01.jpg'),
(180, 'https://render-us.worldofwarcraft.com/icons/56/trade_leatherworking.jpg'),
(181, 'https://render-us.worldofwarcraft.com/icons/56/trade_alchemy.jpg'),
(182, 'https://render-us.worldofwarcraft.com/icons/56/inv_inscription_tradeskill01.jpg'),
(183, 'https://render-us.worldofwarcraft.com/icons/56/trade_archaeology.jpg'),
(184, 'https://render-us.worldofwarcraft.com/icons/56/trade_herbalism.jpg'),
(185, 'https://render-us.worldofwarcraft.com/icons/56/inv_pick_02.jpg'),
(186, 'https://render-us.worldofwarcraft.com/icons/56/trade_tailoring.jpg'),
(187, 'https://render-us.worldofwarcraft.com/icons/56/trade_engineering.jpg'),
(188, 'https://render-us.worldofwarcraft.com/icons/56/sha_spell_warlock_demonsoul.jpg'),
(189, 'https://render-us.worldofwarcraft.com/icons/56/trade_engraving.jpg'),
(190, 'https://render-us.worldofwarcraft.com/icons/56/inv_misc_food_15.jpg'),
(191, 'https://render-us.worldofwarcraft.com/icons/56/sanctum_features_buildabom.jpg'),
(192, 'https://render-us.worldofwarcraft.com/icons/56/trade_fishing.jpg'),
(193, 'https://render-us.worldofwarcraft.com/icons/56/inv_mace_1h_bastionquest_b_01.jpg'),
(194, 'https://render-us.worldofwarcraft.com/icons/56/inv_blacksmithing_815_khazgorianhammer.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `mounts`
--

DROP TABLE IF EXISTS `mounts`;
CREATE TABLE IF NOT EXISTS `mounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `description` text COLLATE utf8_bin NOT NULL,
  `source` int(11) NOT NULL,
  `faction` int(11) NOT NULL,
  `media` int(11) NOT NULL,
  `player` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1_Mounts` (`player`),
  KEY `fk2_Mounts` (`source`),
  KEY `fk3_Mounts` (`faction`),
  KEY `fk4_Mounts` (`media`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `mounts`
--

INSERT INTO `mounts` (`id`, `name`, `description`, `source`, `faction`, `media`, `player`) VALUES
(1, 'Armored Brown Bear', 'Your cries of \"For the Horde!\" will be much more menacing from atop a heavily armored bear that\'s barreling down on Alliance at top speed.', 1, 1, 146, 1),
(2, 'Armored Brown Bear', 'This ursine mount\'s swiftness is enhanced by its heavy armor, imbued with magic from Kirin Tor magi.', 1, 2, 147, 1),
(3, 'Black War Bear', 'This formidable mount is bought with the blood of Alliance leaders, but you would have killed them for free.', 2, 1, 148, 2),
(4, 'Black War Bear', 'Killing leaders of the Horde is its own reward, but this intimidating beast is an excellent memento.', 2, 2, 149, 1),
(5, 'Mekgineer\'s Chopper', 'Sporting superb engineering craftsmanship, this chopper is one of the few personal vehicles on Azeroth built for two.', 3, 2, 150, 2),
(6, 'Armored Snowy Gryphon', 'The nice thing is that when they attack, blood almost never stains their feathers. -Mei Francis', 1, 2, 151, 1),
(7, 'Armored Blue Wind Rider', 'They are not native to Northrend, but they have adapted extremely well to the cold. -Mei Francis', 1, 1, 152, 2),
(8, 'Traveler\'s Tundra Mammoth', 'Ancient manuscripts depict merchants traveling across the frozen tundra with these colossal beasts, leading scholars to believe they are the first domesticated mounts.', 1, 2, 153, 1),
(9, 'Traveler\'s Tundra Mammoth', 'Riders and merchants alike trust only this strong and steadfast mammoth to carry them safely across the frigid tundra.', 1, 1, 154, 1),
(10, 'Grand Black War Mammoth', 'Once used by giants to carry heavily armored warriors, these grand war mammoths can easily bear more than one rider of less-than-giant stature.', 4, 2, 155, 1),
(11, 'Grand Black War Mammoth', 'Rarely, a war mammoth is born stronger and more tractable than its peers. These mammoths are dubbed \"grand\" and carry more than one rider to the front lines.', 4, 1, 156, 2),
(12, 'Grand Ice Mammoth', 'At home on the narrow, icy trails of the Storm Peaks, these mammoths are the only mounts the ice giants trust to carry large loads and multiple riders.', 1, 1, 157, 2),
(13, 'Grand Ice Mammoth', 'Due to high winds and avalanches, the Sons of Hodir recommend bringing at least two companions if venturing into the dangerous Storm Peaks.', 1, 2, 158, 2),
(14, 'Blue Dragonhawk', 'Dragonhawks are among the deadliest known predators. They are able to spot prey from great distances, rush in, and rend with both talon and beak.', 2, 2, 159, 2),
(15, 'Red Dragonhawk', 'Dragonhawks are among the deadliest known predators. They are able to spot prey from great distances, rush in, and rend with both talon and beak.', 2, 1, 160, 2),
(16, 'Stormwind Steed', 'Although raised in Stormwind, this breed showed unusual aptitude for the cold weather in Icecrown.', 1, 2, 161, 2),
(17, 'Darkspear Raptor', 'This raptor breed was originally cultivated by the Drakkari, but has since been adopted by the Darkspear tribe in Northrend.', 1, 1, 162, 2),
(18, 'Darnassian Nightsaber', 'When a night elf\'s mount perishes, it is custom for the rider to keep one of the saber\'s great fangs as a token of remembrance.', 1, 2, 163, 2),
(19, 'Ironforge Ram', 'Wildhammer Fact Checker claims that this specific breed was the first type of ram domesticated by the dwarves when they settled in Dun Morogh.', 1, 2, 164, 1),
(20, 'Gnomeregan Mechanostrider', 'When you absolutely, positively have to be there right now. -Rillie Spindlenut', 1, 2, 165, 1),
(21, 'Exodar Elekk', 'Exodar Elekk are elekk which have been anointed by O\'ros, the Naaru who journeyed from Draenor to Azeroth with Velen and his followers.', 1, 2, 166, 1),
(22, 'Orgrimmar Wolf', 'For a brief time, many orcs would declare themselves \"hungry like an Orgrimmar wolf.\" Some blood elves still use the phrase ironically.', 1, 1, 167, 1),
(23, 'Forsaken Warhorse', 'When fallen heroes are raised into undeath, so too are their horses.', 1, 1, 168, 1),
(24, 'Thunder Bluff Kodo', 'An alpha member of the herd, this kodo is a prime example of the mighty creatures which thunder through Mulgore.', 1, 1, 169, 1),
(25, 'Silvermoon Hawkstrider', 'So I says I feel like chicken tonight. Next thing, I am flat out and that thing is trying to see what my eyes taste like. -Bozzle Blastbolt', 1, 1, 170, 1),
(26, 'White Kodo', '\"The kodo is the backbone of our people. Wherever the Horde goes - in trade, settlement, or war - there you will find the mighty kodo.\" - Harb Clawhoof', 1, 1, 171, 1),
(27, 'Black Wolf', 'Can howl loudly enough to be heard for miles.', 1, 1, 172, 1),
(28, 'Venomhide Ravasaur', 'You raised this thing from a scaly pup - Mor\'vek would be so proud. But he\'d still make you clean up the mess it made in the corner.', 5, 1, 173, 2),
(29, 'Swift Moonsaber', 'Many night elves revere these proud mounts because their shimmering coats symbolize the goddess of the moon, Elune.', 1, 2, 174, 2);

-- --------------------------------------------------------

--
-- Structure de la table `players`
--

DROP TABLE IF EXISTS `players`;
CREATE TABLE IF NOT EXISTS `players` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `isConnected` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `media` int(11) NOT NULL,
  `class` int(11) NOT NULL,
  `race` int(11) NOT NULL,
  `profession` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1_Players` (`class`),
  KEY `fk2_Players` (`race`),
  KEY `fk3_Players` (`profession`),
  KEY `fk4_Players` (`media`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `players`
--

INSERT INTO `players` (`id`, `name`, `isConnected`, `level`, `media`, `class`, `race`, `profession`) VALUES
(1, 'Geekixel', 1, 30, 143, 1, 1, 1),
(2, 'Oxii', 0, 60, 144, 4, 8, 2);

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
-- Structure de la table `powertypes`
--

DROP TABLE IF EXISTS `powertypes`;
CREATE TABLE IF NOT EXISTS `powertypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `powertypes`
--

INSERT INTO `powertypes` (`id`, `name`) VALUES
(1, 'Rage'),
(2, 'Mana'),
(3, 'Focus'),
(4, 'Energy'),
(5, 'Runic Power'),
(6, 'Fury');

-- --------------------------------------------------------

--
-- Structure de la table `professions`
--

DROP TABLE IF EXISTS `professions`;
CREATE TABLE IF NOT EXISTS `professions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `description` text COLLATE utf8_bin NOT NULL,
  `type` int(11) NOT NULL,
  `media` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1_Professions` (`media`),
  KEY `fk2_Professions` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `professions`
--

INSERT INTO `professions` (`id`, `name`, `description`, `type`, `media`) VALUES
(1, 'Mining', 'Higher mining skill allows you to harvest more difficult minerals nodes around the world. If you cannot harvest a specific mineral, then increase your skill by mining easier to mine minerals in lower level areas.', 1, 1),
(2, 'Blacksmithing', 'Higher smithing skill allows you to learn higher level smithing plans. Blacksmithing plans can be found on trainers around the world as well as from quests and monsters.', 1, 177),
(3, 'Skinning', 'Higher skinning skill allows you to skin hides from higher level monsters around the world.', 1, 178),
(4, 'Jewelcrafting', 'Higher jewelcrafting skill allows you to learn higher level jewelcrafting plans. Jewelcrafting plans can be found on trainers around the world as well as from quests and monsters.', 1, 179),
(5, 'Leatherworking', 'Higher leatherworking skill allows you to learn higher level leatherworking patterns. Leatherworking patterns can be found on trainers around the world as well as from quests and monsters.', 1, 180),
(6, 'Alchemy', 'Higher alchemy skill allows you to learn higher level alchemy recipes. Alchemy recipes can be found on trainers around the world as well as from quests and monsters.', 1, 181),
(7, 'Inscription', 'Higher inscription skill allows you to learn more advanced inscriptions. Inscriptions are acquired from learned scribes throughout the world.', 1, 182),
(8, 'Archaeology', 'Higher archaeology skill allows you to complete more valuable artifacts and collect more artifact fragments. Fragments can be found in ruins and dig sites.', 2, 183),
(9, 'Herbalism', 'Higher herbalism skill allows you to harvest more difficult herbs around the world. If you cannot harvest a specific herb, then increase your skill by harvesting easier to gather herbs in lower level areas.', 1, 184),
(10, 'Mining', 'Higher mining skill allows you to harvest more difficult minerals nodes around the world. If you cannot harvest a specific mineral, then increase your skill by mining easier to mine minerals in lower level areas.', 1, 185),
(11, 'Tailoring', 'Higher tailoring skill allows you to learn higher level tailoring patterns. Tailoring patterns can be found on trainers around the world as well as from quests and monsters.', 1, 186),
(12, 'Engineering', 'Higher engineering skill allows you to learn higher level engineering schematics. Schematics can be found on trainers around the world as well as from quests and monsters.', 1, 187),
(13, 'Soul Cyphering', 'New soul cyphers can be found within the Maw.', 1, 188),
(14, 'Enchanting', 'Higher enchanting skill allows you to learn more powerful formulae. Formulae can be found on trainers around the world as well as from quests and monsters.', 1, 189),
(15, 'Cooking', 'Higher cooking skill allows you to learn higher level cooking recipes. Recipes can be found on trainers around the world as well as from quests and as drops from monsters.', 2, 190),
(16, 'Abominable Stitching', 'Parts can be found on monsters in the Shadowlands, and from quests.', 1, 191),
(17, 'Fishing', 'Higher fishing skill increases your chance of catching fish in bodies of water around the world. If you are having trouble catching fish in a given area, move to a lower level area or purchase a fishing lure and try again.', 2, 192),
(18, 'Ascension Crafting', 'Recipes can be obtained through the Path of Ascension feature.', 1, 193),
(19, 'Stygia Crafting', 'Must be in possession of Soulforger\'s Tools.', 1, 194);

-- --------------------------------------------------------

--
-- Structure de la table `qualities`
--

DROP TABLE IF EXISTS `qualities`;
CREATE TABLE IF NOT EXISTS `qualities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `qualities`
--

INSERT INTO `qualities` (`id`, `name`) VALUES
(1, 'Uncommon'),
(2, 'Common'),
(3, 'Epic'),
(4, 'Poor'),
(5, 'Rare');

-- --------------------------------------------------------

--
-- Structure de la table `races`
--

DROP TABLE IF EXISTS `races`;
CREATE TABLE IF NOT EXISTS `races` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `faction` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk1_Races` (`faction`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `races`
--

INSERT INTO `races` (`id`, `name`, `faction`) VALUES
(1, 'Human', 2),
(2, 'Orc', 1),
(3, 'Dwarf', 2),
(4, 'Night Elf', 2),
(5, 'Undead', 1),
(6, 'Tauren', 1),
(7, 'Gnome', 2),
(8, 'Troll', 1),
(9, 'Goblin', 1),
(10, 'Blood Elf', 1),
(11, 'Draenei', 2),
(12, 'Worgen', 2),
(13, 'Pandaren', 1),
(14, 'Nightborne', 1),
(15, 'Highmountain Tauren', 1),
(16, 'Void Elf', 2),
(17, 'Lightforged Draenei', 2),
(18, 'Zandalari Troll', 1),
(19, 'Kul Tiran', 2),
(20, 'Dark Iron Dwarf', 2),
(21, 'Vulpera', 1),
(22, 'Mag\'har Orc', 1),
(23, 'Mechagnome', 2),
(24, 'Pandaren', 2);

-- --------------------------------------------------------

--
-- Structure de la table `sources`
--

DROP TABLE IF EXISTS `sources`;
CREATE TABLE IF NOT EXISTS `sources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `sources`
--

INSERT INTO `sources` (`id`, `name`) VALUES
(1, 'Vendor'),
(2, 'Achievement'),
(3, 'Profession'),
(4, 'Drop'),
(5, 'Quest');

-- --------------------------------------------------------

--
-- Structure de la table `types`
--

DROP TABLE IF EXISTS `types`;
CREATE TABLE IF NOT EXISTS `types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `types`
--

INSERT INTO `types` (`id`, `name`) VALUES
(1, 'Primary'),
(2, 'Secondary');

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
-- Contraintes pour la table `classes`
--
ALTER TABLE `classes`
  ADD CONSTRAINT `fk1_Classes` FOREIGN KEY (`media`) REFERENCES `medias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2_Classes` FOREIGN KEY (`powerType`) REFERENCES `powertypes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `fk1_Items` FOREIGN KEY (`itemClass`) REFERENCES `itemclasses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2_Items` FOREIGN KEY (`media`) REFERENCES `medias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk3_Items` FOREIGN KEY (`quality`) REFERENCES `qualities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `mounts`
--
ALTER TABLE `mounts`
  ADD CONSTRAINT `fk1_Mounts` FOREIGN KEY (`player`) REFERENCES `players` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2_Mounts` FOREIGN KEY (`source`) REFERENCES `sources` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk3_Mounts` FOREIGN KEY (`faction`) REFERENCES `factions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk4_Mounts` FOREIGN KEY (`media`) REFERENCES `medias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `players`
--
ALTER TABLE `players`
  ADD CONSTRAINT `fk1_Players` FOREIGN KEY (`class`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2_Players` FOREIGN KEY (`race`) REFERENCES `races` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk3_Players` FOREIGN KEY (`profession`) REFERENCES `professions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk4_Players` FOREIGN KEY (`media`) REFERENCES `medias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `playersitems`
--
ALTER TABLE `playersitems`
  ADD CONSTRAINT `fk1_PlayersItems` FOREIGN KEY (`item`) REFERENCES `items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2_PlayersItems` FOREIGN KEY (`player`) REFERENCES `players` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `professions`
--
ALTER TABLE `professions`
  ADD CONSTRAINT `fk1_Professions` FOREIGN KEY (`media`) REFERENCES `medias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk2_Professions` FOREIGN KEY (`type`) REFERENCES `types` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `races`
--
ALTER TABLE `races`
  ADD CONSTRAINT `fk1_Races` FOREIGN KEY (`faction`) REFERENCES `factions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
