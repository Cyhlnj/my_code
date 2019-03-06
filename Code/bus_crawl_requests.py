import requests
import time
from bs4 import BeautifulSoup
import json
from collections import Counter
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36',
}


def parse_first_page(url):
    r = requests.get(url, headers=headers)
    soup = BeautifulSoup(r.text, 'lxml')
    # 查找得到所有的以数字开头的链接
    number_a_list = soup.select('.bus_kt_r1 > a')
    char_a_list = soup.select('.bus_kt_r2 > a')
    # 提取a里面的href
    a_list = number_a_list + char_a_list
    href_list = []
    for oa in a_list:
        href = url.rstrip('/') + oa['href']
        href_list.append(href)
    return href_list


def parse_second_page(url, href):
    r = requests.get(url=href, headers=headers)
    soup = BeautifulSoup(r.text, 'lxml')
    # 查找得到所有的公交链接
    bus_a_list = soup.select('#con_site_1 > a')
    href_list = []
    for oa in bus_a_list:
        href = url.rstrip('/') + oa['href']
        href_list.append(href)
    return href_list


def parse_third_page(href, fp):
    r = requests.get(href, headers=headers)
    soup = BeautifulSoup(r.text, 'lxml')
    route_name = soup.select('.bus_i_t1 > h1')[0].string
    print('正在爬取---%s---...' % route_name)
    # 上行总个数
    up_total = soup.select('.bus_line_top > span')[0].string.strip('共站').strip()
    # 上行总站牌
    up_name_list = []
    number = int(up_total)
    up_a_list = soup.select('.bus_site_layer > div > a')[:number]
    for oa in up_a_list:
        up_name_list.append(oa.string)
    # 下行总个数
    # 下行总站牌
    down_a_list = soup.select('.bus_site_layer > div > a')[number:]
    down_total = len(down_a_list)
    down_name_list = []
    for oa in down_a_list:
        down_name_list.append(oa.string)
    name_list = up_name_list + down_name_list
    name_list = list(set(name_list))
    return name_list


# time.sleep(1)

def main():
    url = 'http://beijing.8684.cn/'
    number_char_list = parse_first_page(url)
    all_name_dict = {}
    fp = open('北京.txt', 'w', encoding='utf8')
    # 解析二级页面
    for href in number_char_list:
        bus_href_list = parse_second_page(url, href)
        # 遍历所有的公交详情页，获取每一路公交的详细信息
        for href_detail in bus_href_list:
            name_list = parse_third_page(href_detail, fp)
            name_dict = dict(zip(name_list, [1 for i in range(len(name_list)+1)]))
            x, y = Counter(all_name_dict), Counter(name_dict)
            all_name_dict = dict(x+y)
    print(all_name_dict)
    string = json.dumps(all_name_dict, ensure_ascii=False)
    fp.write(string + '\n')
    fp.close()


if __name__ == '__main__':
    main()
