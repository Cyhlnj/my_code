# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html
import pymysql

class BusPipeline(object):
    def open_spider(self, spider):
        self.connect = pymysql.Connect(
            host="localhost",
            port=3306,
            user="root",
            passwd="",
            db="mysql",
            charset="utf8"
        )
        self.cursor = self.connect.cursor()

    def process_item(self, item, spider):
        sql = "Insert Into Bus1(SIteName, Line_Num) values ('%s','%d')"
        self.cursor.execute(sql % (item['name'], item['line']))
        self.connect.commit()
        return item

    def close_spider(self,spider):
        self.cursor.close()
        self.connect.close()
