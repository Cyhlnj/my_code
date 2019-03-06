# -*- coding: utf-8 -*-
import scrapy
from ..items import BusItem
import re


class BusSpider(scrapy.Spider):
    name = "bus"
    # allowed_domains = ["https://beijing.8684.cn/sitemap1"]
    headers = {
        'User-Agent': "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36",
    }
    start_urls = ['https://beijing.8684.cn/sitemap'+str(i) for i in range(1, 25)]

    def parse(self, response):
        url = "https://beijing.8684.cn"
        site_a_list = response.xpath('//div[@id="con_site_1"]/a/@href').extract()
        for site in site_a_list:
            site_url = url + site
            yield scrapy.Request(url=site_url, callback=self.parse_detail, headers=self.headers)
        pass

    def parse_detail(self, response):
        item = BusItem()
        name = response.xpath('//span[@class="bus_i_t1"]/h1/text()').extract_first()
        line = response.xpath('//div[@class="bus_i_t3"]/text()').extract_first()
        line = re.search('\[(\d*)条\]', line, re.S).group(1)
        line = int(line)
        print(name, line)
        item['name'] = name
        item['line'] = line
        yield item
