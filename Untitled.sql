-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: his
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_drug`
--

DROP TABLE IF EXISTS `t_drug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_drug` (
  `id` int(11) NOT NULL COMMENT '药品',
  `name` varchar(45) DEFAULT NULL COMMENT '名称',
  `chufang` int(11) NOT NULL DEFAULT '0' COMMENT '是否处方药',
  `code` varchar(45) DEFAULT NULL COMMENT '编号',
  `social_code` varchar(45) DEFAULT NULL,
  `self_code` varchar(45) DEFAULT NULL,
  `specification` varchar(45) DEFAULT NULL COMMENT '规格',
  `price` double DEFAULT NULL COMMENT '价格',
  `status` int(11) DEFAULT NULL COMMENT '状态：1=上架，2=下架',
  `in_social` int(11) DEFAULT '0' COMMENT '是否录入社保系统',
  `store` int(11) DEFAULT NULL COMMENT '库存',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_drug`
--

LOCK TABLES `t_drug` WRITE;
/*!40000 ALTER TABLE `t_drug` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_drug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_patient`
--

DROP TABLE IF EXISTS `t_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `diannaohao` varchar(45) NOT NULL COMMENT '社保电脑号',
  `social_code` varchar(45) DEFAULT NULL,
  `cardnum` varchar(45) NOT NULL COMMENT '身份证号',
  `name` varchar(45) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(45) DEFAULT NULL COMMENT '性别',
  `age` varchar(45) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `address` varchar(45) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `diannaohao_UNIQUE` (`diannaohao`),
  UNIQUE KEY `cardnum_UNIQUE` (`cardnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_patient`
--

LOCK TABLES `t_patient` WRITE;
/*!40000 ALTER TABLE `t_patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_prescription`
--

DROP TABLE IF EXISTS `t_prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_prescription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) DEFAULT NULL COMMENT '医生id',
  `patient_register_id` int(11) DEFAULT NULL COMMENT '病人挂号单id',
  `diagnosis` varchar(255) NOT NULL COMMENT '诊断结果，必填',
  `remark` varchar(1000) DEFAULT NULL COMMENT '医嘱，选填',
  `prescription_price` varchar(45) DEFAULT NULL COMMENT '出诊费',
  `drugs_price` varchar(45) DEFAULT NULL COMMENT '药品费',
  `total_price` varchar(45) DEFAULT NULL COMMENT '总额',
  `yibao_price` varchar(45) DEFAULT NULL COMMENT '医保报销',
  `self_price` varchar(45) DEFAULT NULL COMMENT '自费',
  `status` int(11) DEFAULT NULL COMMENT '状态：1=诊断，2=付费，-1=退费',
  `prescription_time` datetime DEFAULT NULL COMMENT '诊断时间',
  `pay_time` datetime DEFAULT NULL COMMENT '收费时间',
  `return_time` datetime DEFAULT NULL COMMENT '退费时间',
  `num` varchar(45) DEFAULT NULL COMMENT '处方病历编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_prescription`
--

LOCK TABLES `t_prescription` WRITE;
/*!40000 ALTER TABLE `t_prescription` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_prescription_drugs`
--

DROP TABLE IF EXISTS `t_prescription_drugs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_prescription_drugs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prescription_id` int(11) DEFAULT NULL COMMENT '处方id',
  `drug_id` int(11) DEFAULT NULL COMMENT '药品id',
  `drug_price` varchar(45) DEFAULT NULL COMMENT '药品单价',
  `remark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_prescription_drugs`
--

LOCK TABLES `t_prescription_drugs` WRITE;
/*!40000 ALTER TABLE `t_prescription_drugs` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_prescription_drugs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_register`
--

DROP TABLE IF EXISTS `t_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) DEFAULT NULL COMMENT '患者id',
  `code` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '状态：0=未交费，1=已交费，2=已就诊，-1=已退费',
  `price` double DEFAULT NULL,
  `jijin_price` double DEFAULT NULL,
  `yibao_price` double DEFAULT NULL,
  `self_price` double DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '生成时间',
  `pay_time` datetime DEFAULT NULL COMMENT '付费时间',
  `business_code` varchar(45) DEFAULT NULL COMMENT '本地机构结算业务号',
  `business_finish_code` varchar(45) DEFAULT NULL,
  `finish_code` varchar(45) DEFAULT NULL COMMENT '社保结算流水号',
  `use_time` datetime DEFAULT NULL COMMENT '就诊时间',
  `return_time` datetime DEFAULT NULL COMMENT '退费时间',
  `return_business_code` varchar(45) DEFAULT NULL,
  `return_finish_code` varchar(45) DEFAULT NULL,
  `date_string` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  UNIQUE KEY `business_code_UNIQUE` (`business_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_register`
--

LOCK TABLES `t_register` WRITE;
/*!40000 ALTER TABLE `t_register` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_register_type`
--

DROP TABLE IF EXISTS `t_register_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_register_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_register_type`
--

LOCK TABLES `t_register_type` WRITE;
/*!40000 ALTER TABLE `t_register_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_register_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sell_drug`
--

DROP TABLE IF EXISTS `t_sell_drug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sell_drug` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL COMMENT '售药编号',
  `doctor_id` int(11) DEFAULT NULL COMMENT '售药医师id',
  `patient_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '状态：1=开药，2=收费，-1=退费',
  `kaiyao_time` datetime DEFAULT NULL COMMENT '开药时间',
  `pay_time` datetime DEFAULT NULL,
  `return_time` datetime DEFAULT NULL COMMENT '退费时间',
  `total_price` double DEFAULT NULL COMMENT '总额',
  `jijin_price` double DEFAULT NULL,
  `yibao_price` double DEFAULT NULL COMMENT '医保报销',
  `self_price` double DEFAULT NULL COMMENT '自费',
  `business_code` varchar(45) DEFAULT NULL,
  `business_finish_code` varchar(45) DEFAULT NULL,
  `finish_code` varchar(45) DEFAULT NULL,
  `return_business_code` varchar(45) DEFAULT NULL,
  `return_finish_code` varchar(45) DEFAULT NULL,
  `date_string` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sell_drug`
--

LOCK TABLES `t_sell_drug` WRITE;
/*!40000 ALTER TABLE `t_sell_drug` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sell_drug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sell_drugs`
--

DROP TABLE IF EXISTS `t_sell_drugs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sell_drugs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sell_drug_id` int(11) DEFAULT NULL COMMENT '售药单id',
  `drug_id` int(11) DEFAULT NULL COMMENT '药品id',
  `drug_price` double DEFAULT NULL COMMENT '药品单价',
  `num` int(11) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sell_drugs`
--

LOCK TABLES `t_sell_drugs` WRITE;
/*!40000 ALTER TABLE `t_sell_drugs` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sell_drugs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(45) DEFAULT NULL COMMENT '账号 ',
  `name` varchar(45) DEFAULT NULL COMMENT '名称',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `yibao_code` varchar(45) DEFAULT NULL COMMENT '医保编码',
  `yibao_psw` varchar(45) DEFAULT NULL COMMENT '医保密码',
  `type` int(11) DEFAULT NULL COMMENT '账号类型：0=超级管理员，1=普通员工，2=医生，3=护士',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'his'
--

--
-- Dumping routines for database 'his'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-22 14:48:23
