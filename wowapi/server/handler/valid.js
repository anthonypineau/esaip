module.exports = {
    isValidId(req, res, next) {
        if(!isNaN(req.params.id)) return next();
        next(new Error('Invalid ID'));
    },
    validMedia(media){
        const hasLink = typeof media.link == 'string' && media.link.trim() != '';
        return hasLink;
    },
    validSource(source){
        const hasName = typeof source.name == 'string' && source.name.trim() != '';
        return hasName;
    },
    validFaction(faction){
        const hasName = typeof faction.name == 'string' && faction.name.trim() != '';
        return hasName;
    },
    validPowerType(powerType){
        const hasName = typeof powerType.name == 'string' && powerType.name.trim() != '';
        return hasName;
    },
    validType(type){
        const hasName = typeof type.name == 'string' && type.name.trim() != '';
        return hasName;
    },
    validQuality(quality){
        const hasName = typeof quality.name == 'string' && quality.name.trim() != '';
        return hasName;
    },
    validClass(classe){
        const hasName = typeof classe.name == 'string' && classe.name.trim() != '';
        const hasPowerType = typeof classe.powerType == 'string' && classe.powerType.trim() != '';
        const hasMedia = typeof classe.media == 'string' && classe.media.trim() != '';
        return hasName && hasPowerType && hasMedia;
    },
    validItemClass(itemClass){
        const hasName = typeof itemClass.name == 'string' && itemClass.name.trim() != '';
        return hasName;
    },
    validItem(item){
        const hasName = typeof item.name == 'string' && item.name.trim() != '';
        const hasQuality = typeof item.quality == 'string' && item.quality.trim() != '';
        const hasLevel = !isNaN(item.level);
        const hasRequiredLevel = !isNaN(item.requiredLevel);
        const hasPurchasePrice = !isNaN(item.purchasePrice);
        const hasSellPrice = !isNaN(item.sellPrice);
        const hasIsEquippable = !isNaN(item.isEquippable);
        const hasIsStackable = !isNaN(item.isStackable);
        const hasMedia = !isNaN(item.media);
        const hasItemClass = !isNaN(item.itemClass);
        return hasName && hasQuality && hasLevel && hasRequiredLevel && hasPurchasePrice 
        && hasSellPrice && hasIsEquippable && hasIsStackable && hasMedia && hasItemClass;
    },
    validMount(mount){
        const hasName = typeof mount.name == 'string' && mount.name.trim() != '';
        const hasDescription = typeof mount.description == 'string' && mount.description.trim() != '';
        const hasSource = !isNaN(mount.source);
        const hasFaction = !isNaN(mount.faction);
        const hasMedia = !isNaN(mount.media);
        const hasPlayer = !isNaN(mount.player);
        return hasName && hasDescription && hasSource && hasFaction && hasMedia && hasPlayer;
    },
    validPlayer(player){
        const hasUsername = typeof player.username == 'string' && player.username.trim() != '';
        const hasIsConnected = !isNaN(player.isConnected);
        const hasLevel = !isNaN(player.level);
        const hasMedia = !isNaN(player.media);
        const hasClass = !isNaN(player.class);
        const hasRace = !isNaN(player.race);
        const hasProfession = !isNaN(player.profession);
        return hasUsername && hasIsConnected && hasLevel && hasMedia && hasClass && hasRace && hasProfession;
    },
    validPlayerItem(playerItem){
        const hasItem = !isNaN(playerItem.item);
        const hasPlayer = !isNaN(playerItem.player);
        const hasNumberInInventory = !isNaN(playerItem.numberInInventory);
        return hasItem && hasPlayer && hasNumberInInventory;
    },
    validProfession(profession){
        const hasName = typeof profession.name == 'string' && profession.name.trim() != '';
        const hasDescription = typeof profession.description == 'string' && profession.description.trim() != '';
        const hasType = !isNaN(profession.type);
        const hasMedia = !isNaN(profession.media);
        return hasName && hasDescription && hasType && hasMedia;
    },
    validRace(race){
        const hasName = typeof race.name == 'string' && race.name.trim() != '';
        const hasFaction = !isNaN(race.faction);
        return hasName && hasFaction;
    }
}