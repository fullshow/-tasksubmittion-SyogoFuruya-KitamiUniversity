# 【課題提出】古屋翔悟／北見工業大学

# 使用した技術要素
- Java(TM) SE Runtime Environment (build 1.8.0_101-b13)
- Spring Framework ( spring boot + thymeleaf )
- MongoDB
- javascript

#### ※全て３２bit、動作環境はWindows10、ブラウザはChrome

# 全体の設計・構成についての説明
#### 下記にプロジェクト構成を記述致します。
#### プロジェクト名:AppTodoExamCleared

### AppTodoExamCleared/src/main/java/com/   システムのメイン  
- App.java ここにメイン文
- MainController.java 　Todo制御システム本体
#### Todoの入出力制御、Webページの操作を担当しています。

### AppTodoExamCleared/src/main/java/com/mongo   Todoのデータ構造
- MongoTodoData     Todoリストのデータ構造
- MongoTodoListData     Todoのデータ構造
- MongoTodoRepository   Todoリストの操作
#### データ構造は２次元で構成、Todo等のデータはMongoTodoDataにリストとして格納

### AppTodoExamCleared/src/main/java/resources/static/css   Webページ装飾
- folding.css    Todo作成のラッパーボタンのデザイン
- inputform.css   Todo検索とTodoリスト作成に使用したフォームデザイン
- main.css        Web画面の構成デザイン
- nav.css         Web画面の上段に位置するメニュー
- table.css       todo表示、todoの検索結果で使用した表
- todoinput.css   Todo作成用入力フォーム

### AppTodoExamCleared/src/main/java/resources/templates   Webページ本体
- addtodo.html    Todo詳細画面
- search.html     Todo検索画面
- layout.html     メニューバー
- listnew.html    トップ画面、Todoリスト作成と一覧
### layout.htmlはすべてのWebページに設置

### AppTodoExamCleared/src/main/java/resources/templates/check
- alldelete.html    DBのオールクリア

### AppTodoExamCleared/src/main/test/java/com   テスト用

## その他
- application.properties    MongoDB接続プロパティなど
- pom.xml     依存関係まとめ

## 独自機能
Todo詳細画面(addtodo.html)に記録したTodoに通知機能を付与
メモ文を加え、優先度確認等の備考欄とした

## 開発環境のセットアップ手順
1. Spring Tool Suiteのダウンロード、統合開発環境によるアプリ開発
2. MongoDBのインストール　
3. プログラム実行前にcmd.exeで mongo起動、mongod --dbpath c:\mongodb\data --logpath c:\mongodb\logs\mongodb.log
4. application.properties設定 以下の通りで設定
- spring.data.mongodb.host=localhost
- spring.data.mongodb.port=27017
- spring.data.mongodb.database=todo 
5. フリーソフトツールとしてmongobooster-3.5.6を用意、DBの監視とエクスポートのため、接続はapplication.propertiesより
6. Spring Tool Suiteよりプロジェクト実行、Chromeにて動作　トップページ http://localhost:8900/listnew でチェック

####※　エクスポートデータはJSON形式、プロジェクトトップに配置

