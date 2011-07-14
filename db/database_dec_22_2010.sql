SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `cmi_chat` DEFAULT CHARACTER SET utf8 ;
USE `cmi_chat`;

-- -----------------------------------------------------
-- Table `cmi_chat`.`chat_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cmi_chat`.`chat_users` ;

CREATE  TABLE IF NOT EXISTS `cmi_chat`.`chat_users` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nick_name` VARCHAR(50) NULL ,
  `email_user` VARCHAR(100) NOT NULL ,
  `role` VARCHAR(20) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `index_email_user` (`email_user` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cmi_chat`.`chats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cmi_chat`.`chats` ;

CREATE  TABLE IF NOT EXISTS `cmi_chat`.`chats` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `start_time` TIMESTAMP NOT NULL ,
  `end_time` TIMESTAMP NULL ,
  `agent_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Chat_Chat_Users` (`agent_id` ASC) ,
  CONSTRAINT `fk_Chat_Chat_Users`
    FOREIGN KEY (`agent_id` )
    REFERENCES `cmi_chat`.`chat_users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `cmi_chat`.`chat_messages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cmi_chat`.`chat_messages` ;

CREATE  TABLE IF NOT EXISTS `cmi_chat`.`chat_messages` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `message` VARCHAR(250) NOT NULL ,
  `time` TIMESTAMP NOT NULL ,
  `chat_id` INT NOT NULL ,
  `sender_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Chat_Messages_Chat1` (`chat_id` ASC) ,
  INDEX `fk_chat_messages_chat_users1` (`sender_id` ASC) ,
  CONSTRAINT `fk_Chat_Messages_Chat1`
    FOREIGN KEY (`chat_id` )
    REFERENCES `cmi_chat`.`chats` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_messages_chat_users1`
    FOREIGN KEY (`sender_id` )
    REFERENCES `cmi_chat`.`chat_users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
