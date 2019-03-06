# -*- coding: utf-8 -*-
import scrapy
from ..items import KidscodeItem

class ExampleSpider(scrapy.Spider):
    name = "kidscode"
    # allowed_domains = ["example.com"]
    start_urls = ['http://www.kidscode.cn/python/page/' + str(i) for i in range(1, 23)]
    headers = {
        'User-Agent': "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36",

    }

    def parse(self, response):
        url = 'http://www.kidscode.cn'
        site_a_list = response.xpath('//div[@class="xw_title"]/span/a/@href').extract()
        for site_list in site_a_list:
            site_url = url + site_list
            # print(site_url, '!!!!!!!!!!!!!')
            yield scrapy.Request(site_url, callback=self.parse_detail, headers=self.headers)

    def parse_detail(self, response):
        item = KidscodeItem()
        title = response.xpath('//div[@class="nr_c"]/h2/text()').extract_first()
        time = response.xpath('//div[@class="nr_c"]/section/time/text()').extract_first()
        the_url = response.url
        print('crawling... ', the_url, title)
        item['title'] = title
        item['time'] = time
        item['the_url'] = the_url
        yield item