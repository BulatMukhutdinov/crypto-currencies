<a href="https://github.com/BulatMukhutdinov/crypto-stock/blob/master/CryptoStock.apk?raw=true" download>Click to download the apk</a>
# Описание
1. На главном экране приложение отображает список криптовалют: символ, имя и цена в usd. Список должен подгружаться батчами (страницами): сначала 20 валют, при скроллинге – ещё 20 и т.д. (эндпоинты /assets и /assets/{assetKey}/metrics/market-data можно получать с помощью API: https://messari.io/api/docs)

2. При нажатии на элемент списка открывается экран деталей. На нём отображаются символ, имя и цена, плюс слоган (tagline), подробное описание (project_details) и ссылки (official_links) (эндпоинты /assets, /assets/{assetKey}/metrics/market-data и /assets/{assetKey}/profile)

3. На экране деталей также необходимо отобразить график изменения цены валюты за последний месяц (эндпоинт /assets/{assetKey}/metrics/{metricID}/time-series)

# Допущения
* Для улучшения пользовательского опыта и для уменьшения кол-ва запросов в сеть, tagline, project_description и official_links грузятся на экране списка
* Для улучшения читабельности на экране списка цена округляется до 2 знаков после запятой
* Для ускорения разраотки была использована библиотека, для отрисовки графиков изменения цены, которая работает только с float значениями. При конвертации из BigDecimal в float может теряться точность
