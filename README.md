# リポジトリをcloneし、アプリを実行するための手順

## 環境構築

### Windows
#### Chocolatey
##### 1.Windows PowerShellを管理者として起動し、以下のコマンドを実行する。  
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = 
[System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))

<br>

##### 2.コマンドプロンプトを管理者として起動し、以下のコマンドを実行する。  
choco install -y git
choco install -y corretto21jdk
choco install -y maven
choco install -y docker-desktop
choco install -y vscode
choco install -y pnpm
choco install -y nodejs-lts

<br><br>

### macOS
#### Homebrew
##### 1.ターミナルを起動し、以下のコマンドを実行する。
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)"

<br>

##### 2.ターミナルを起動し、以下のコマンドを実行する。
brew install git
brew install corretto@21
brew install maven
brew install docker --cask
brew install visual-studio-code --cask
brew install pnpm
brew install nodebrew

<br>

##### 3.nodebrewの初期設定をする。
nodebrew setup
echo 'export PATH=$PATH:$HOME/.nodebrew/current/bin' >> ~/.bash_profile

<br>

##### 4.使用するバージョンをセットする。
事前にLTSバージョンを確認しておく (https://github.com/nodejs/Release?tab=readme-ov-file#release-schedule)

<br>

##### 5.nodeのインストール＋使用設定をする。また、target versionはLTSバージョンに置き換える。
nodebrew install-binary <target version>
nodebrew use <target version>

<br>

##### 6.nodeコマンドでバージョン確認
node -v

<br><br>

## リポジトリのcloneとアプリの起動

1.Git Bashで以下のコマンドを入力する。
git clone https://github.com/yugo-tamura/monocrea-technical-training.git

<img width="947" height="404" alt="image" src="https://github.com/user-attachments/assets/1975e79a-1615-427f-9a84-6bc6bc555392" />

2.以下のコマンドを入力し、mono-backディレクトリに移動する。

cd monocrea-technical-training/mono-back

3.以下のコマンドを入力し、バックエンドアプリを起動する。

./mvnw quarkus:dev

3.以下のコマンドを入力し、mono-frontディレクトリに移動する。

cd ../mono-fron

4.以下のコマンドを入力し、json-serverをインストールする。

npm install -g json-server@0.17.4

5.以下のコマンドを入力し、json-serverを起動する。

npx json-server --watch db.json -p 3000

6.以下のコマンドを入力し、依存関係をインストールする。

pnpm install

7.以下のコマンドを入力し、フロントエンドアプリを起動する。

pnpm dev --open


## Frontendアプリ

1.以下のパスのファイルの該当の行をコメントアウトする

monocrea-technical-training/mono-front/src/routes/+page.server.ts

40行目~46行目

monocrea-technical-training/mono-front/src/routes/detail/[userId]/+page.server.ts

10行目  
31行目~35行目  
53行目~57行目
73行目~75行目

<br>
2.以下のパスのファイルの該当の行のコメントアウトを外す

monocrea-technical-training/mono-front/src/routes/+page.server.ts

27行目~37行目

monocrea-technical-training/mono-front/src/routes/detail/[userId]/+page.server.ts

7行目  
24行目~28行目  
46行目~50行目  
68行目~70行目

