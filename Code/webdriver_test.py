import time
from selenium import webdriver


driver = webdriver.Chrome()

driver.get('https://www.80s.tw/movie/list/----g')

with open('movie.csv', 'w', encoding='utf-8') as f:
    f.write('电影名，评分'+'\n')

while True:
    content_list = driver.find_elements_by_xpath("//ul[@class='me1 clearfix']/li")

    for i in content_list:
        text = i.find_element_by_xpath(".//h3[@class='h3']//a").text
        score = i.find_element_by_xpath("//span[@class='poster_score']").text
        print(text, score)
        with open('movie.csv', 'a', encoding='utf-8') as f:
            f.write(text + ',' + score + '\n')
    
    next_url = driver.find_element_by_xpath("//div[@class='pager']//a[contains(text(),'下一页')]").click()

time.sleep(1)
