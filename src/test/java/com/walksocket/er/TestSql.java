package com.walksocket.er;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.junit.jupiter.api.Test;

public class TestSql {

  @Test
  public void testCreateParseAutoIncrement() throws JSQLParserException {
    var ddl = "CREATE TABLE `m_adm_actibook_create_article` (\n"
        + "  `create_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '生成ID',\n"
        + "  `article_id` int(10) unsigned NOT NULL COMMENT '記事ID',\n"
        + "  `type` tinyint(3) unsigned NOT NULL COMMENT '記事タイプ',\n"
        + "  `file_name` text NOT NULL COMMENT '元記事ファイル名',\n"
        + "  `token` text DEFAULT NULL COMMENT 'トークン',\n"
        + "  `create_status` tinyint(3) unsigned NOT NULL COMMENT 'ブック生成ステータス',\n"
        + "  `progress_status` tinyint(3) unsigned NOT NULL COMMENT 'ブック変換ステータス',\n"
        + "  `in_date` datetime NOT NULL COMMENT '登録日時',\n"
        + "  `up_date` datetime NOT NULL COMMENT '更新日時',\n"
        + "  PRIMARY KEY (`create_id`)\n"
        + ") ENGINE=InnoDB AUTO_INCREMENT=33218 DEFAULT CHARSET=utf8mb4 COMMENT='M_ActiBook生成管理テーブル(管理ツール投入用)' ";
    var create = (CreateTable) CCJSqlParserUtil.parse(ddl);
    System.out.println(Json.toJsonStringFriendly(create));
  }

  @Test
  public void testCreateParseGenerated() throws JSQLParserException {
    var ddl = "CREATE TABLE `m_clinic_map_pos` (\n"
        + "  `map_pos_id` bigint(20) unsigned NOT NULL COMMENT 'マップ位置ID',\n"
        + "  `field_id` tinyint(3) unsigned NOT NULL COMMENT '所属領域ID',\n"
        + "  `ladder` tinyint(3) unsigned NOT NULL COMMENT 'クリニカルラダー',\n"
        + "  `map_address` int(10) unsigned NOT NULL COMMENT '番地',\n"
        + "  `map_pos_name` tinytext NOT NULL COMMENT 'マップ番地名',\n"
        + "  `common_flag` tinyint(3) unsigned GENERATED ALWAYS AS (case when `map_address` > 1000000 then 1 else 0 end) VIRTUAL COMMENT '共通フラグ',\n"
        + "  `category_l` text DEFAULT NULL COMMENT '大項目',\n"
        + "  `category_m` text DEFAULT NULL COMMENT '中項目ID',\n"
        + "  `category_s` text DEFAULT NULL COMMENT '小項目',\n"
        + "  `in_date` datetime NOT NULL COMMENT '登録日時',\n"
        + "  `up_date` datetime NOT NULL COMMENT '更新日時',\n"
        + "  `up_admin` tinytext NOT NULL COMMENT '更新者',\n"
        + "  `remarks` mediumtext NOT NULL COMMENT '備考',\n"
        + "  PRIMARY KEY (`map_pos_id`),\n"
        + "  UNIQUE KEY `field_id` (`field_id`,`ladder`,`map_address`)\n"
        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='M_マップ位置（臨床）'";
    var create = (CreateTable) CCJSqlParserUtil.parse(ddl);
    System.out.println(Json.toJsonStringFriendly(create));
  }

  @Test
  public void testCreateParseOnUpdate() throws JSQLParserException {
    var ddl = "CREATE TABLE `m_article3` (\n"
        + "  `article_id` bigint(20) NOT NULL COMMENT '記事ID',\n"
        + "  `file_name` varchar(256) NOT NULL COMMENT 'ファイル名',\n"
        + "  `title` text NOT NULL COMMENT 'タイトル',\n"
        + "  `detail` text NOT NULL COMMENT '詳細',\n"
        + "  `op_date` datetime NOT NULL COMMENT '配信開始日時',\n"
        + "  `ed_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp() COMMENT '配信終了日時',\n"
        + "  `in_date` datetime NOT NULL COMMENT '登録日時',\n"
        + "  `up_date` datetime NOT NULL COMMENT '更新日時',\n"
        + "  PRIMARY KEY (`article_id`),\n"
        + "  UNIQUE KEY `uk_file_name` (`file_name`),\n"
        + "  KEY `k_up_date` (`up_date`)\n"
        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='記事'";
    var create = (CreateTable) CCJSqlParserUtil.parse(ddl);
    System.out.println(Json.toJsonStringFriendly(create));
  }
}
