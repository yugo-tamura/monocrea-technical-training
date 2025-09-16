リポジトリをcloneし、アプリを実行するための手順

環境構築

Windows
Chocolatey
1.Windows PowerShellを管理者として起動し、以下のコマンドを実行する。
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = 
[System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))

2.コマンドプロンプトを管理者として起動し、以下のコマンドを実行する。
choco install -y git
choco install -y corretto21jdk
choco install -y maven
choco install -y docker-desktop
choco install -y vscode
choco install -y pnpm
choco install -y nodejs-lts

macOS
Homebrew
1.ターミナルを起動し、以下のコマンドを実行する。
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)"

2.ターミナルを起動し、以下のコマンドを実行する。
brew install git
brew install corretto@21
brew install maven
brew install docker --cask
brew install visual-studio-code --cask
brew install pnpm
brew install nodebrew

3.nodebrewの初期設定をする。
nodebrew setup
echo 'export PATH=$PATH:$HOME/.nodebrew/current/bin' >> ~/.bash_profile

4.使用するバージョンをセットする。
事前にLTSバージョンを確認しておく (https://github.com/nodejs/Release?tab=readme-ov-file#release-schedule)

5.nodeのインストール＋使用設定をする。また、target versionはLTSバージョンに置き換える。
nodebrew install-binary <target version>
nodebrew use <target version>

6.nodeコマンドでバージョン確認
node -v


リポジトリのclone

1.Git Bashで以下のコマンドを入力する。
git clone https://github.com/yugo-tamura/monocrea-technical-training.git

<img width="947" height="404" alt="image" src="https://github.com/user-attachments/assets/1975e79a-1615-427f-9a84-6bc6bc555392" />


cd monocrea-technical-training








