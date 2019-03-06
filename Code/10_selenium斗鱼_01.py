from selenium import webdriver
import time


def douyu():
    # 创建一个
    driver = webdriver.Chrome()
    driver.get('https://www.douyu.com/g_DOTA2')
    with open('dota.csv', 'w', encoding='utf-8') as f:
        f.write('主播名, 人数' + '\n')
    while True:

        content_list = driver.find_elements_by_xpath("//ul[@id='live-list-contentbox']/li")
        print(content_list)

        for i in content_list:
            print(i)

            author = i.find_element_by_xpath(
                ".//div[@class='mes']//span[contains(@class,'dy-name')]").text
            num = i.find_element_by_xpath(
                ".//div[@class='mes']//span[contains(@class,'dy-num')]").text
            print(author, num)
            with open('dota.csv', 'a', encoding='utf-8') as f:
                f.write(author + ',' + num + '\n')
        next_url = driver.find_elements_by_xpath("//a[@class='shark-pager-next']")
        next_url = next_url[0] if len(next_url) > 0 else None
        if next_url == None:
            break
        next_url.click()
        time.sleep(1)
    driver.quit()


if __name__ == '__main__':
    douyu()
