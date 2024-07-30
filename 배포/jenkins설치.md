1. docker 방식 설치 시
   1) jenkins container 생성 및 구동
       cd /home/ubuntu && mkdir jenkins-data

       sudo ufw allow *8080*/tcp
       sudo ufw reload
       sudo ufw status

       sudo docker run -d -p 8080:8080 -v /home/ubuntu/jenkins-data:/var/jenkins_home --name jenkins jenkins/jenkins:lts
      
       sudo docker logs jenkins

       sudo docker stop jenkins
       sudo docker ps -a

   2) 환경 설정 변경 (매우 중요)
       cd /home/ubuntu/jenkins-data
       
       mkdir update-center-rootCAs

       wget https://cdn.jsdelivr.net/gh/lework/jenkins-update-center/rootCA/update-center.crt -O ./update-center-rootCAs/update-center.crt

       sudo sed -i 's#https://updates.jenkins.io/update-center.json#https://raw.githubusercontent.com/lework/jenkins-update-center/master/updates/tencent/update-center.json#' ./hudson.model.UpdateCenter.xml

       sudo docker restart jenkins

   3) 주요 명령어
       sudo docker start jenkins
       sudo docker stop jenkins
       sudo docker logs jenkins
       sudo docker logs -f jenkins

   4) config 보안 설정 확인(매우 중요)
      vi /home/ubuntu/jenkins-data/config.xml

     <useSecurity>true</useSecurity>
       ...(중략)
     <securityRealm class="hudson.security.HudsonPrivateSecurityRealm">
         <disableSignup>true</disableSignup>
---------------------------------------------------------------------------------------

※ 설치 이후 플러그인 추가 설치에 이상 있는 경우

패키지매니저 방식: cd /var/lib/jenkins (기본 설치 경로)
docker 방식: cd /home/ubuntu/jenkins-data (docker에 마운트된 볼륨 디렉토리)
vi ./hudson.model.UpdateCenter.xml
sudo systemctl restart jenkins 또는 sudo docker restart jenkins (설치 방식에 따라)

---------------------------------------------------------------------------------------
2. linux 기본 방식(패키지매니저) 설치 시

   1) jenkins 설치
      sudo ufw allow 8080/tcp

      sudo ufw reload

      sudo ufw status

      sudo apt-get install openjdk-11-jre

      wget https://pkg.jenkins.io/debian-stable/direct/jenkins_2.414.3_all.deb
  
      sudo dpkg -i jenkins_2.414.3_all.deb

      sudo systemctl status jenkins

      sudo cat /var/lib/jenkins/secrets/initialAdminPassword

      sudo systemctl stop jenkins

   2) jenkins 설치
      cd /var/lib/jenkins

      sudo mkdir update-center-rootCAs
      sudo wget https://cdn.jsdelivr.net/gh/lework/jenkins-update-center/rootCA/update-center.crt -O ./update-center-rootCAs/update-center.crt
      sudo chown -R jenkins:jenkins update-center-rootCAs

      sudo sed -i 's#https://updates.jenkins.io/update-center.json#https://raw.githubusercontent.com/lework/jenkins-update-center/master/updates/tencent/update-center.json#' ./hudson.model.UpdateCenter.xml

      sudo systemctl restart jenkins

   3) 주요 명령어
       sudo systemctl start jenkins
       sudo systemctl stop jenkins
       sudo systemctl status jenkins

   4) config 보안 설정 확인(매우 중요)
      vi /home/ubuntu/jenkins-data/config.xml

     <useSecurity>true</useSecurity>
       ...(중략)
     <securityRealm class="hudson.security.HudsonPrivateSecurityRealm">
         <disableSignup>true</disableSignup>
---------------------------------------------------------------------------------------

※ 참고. 플러그인 관련 파일
/var/lib/jenkins/updates/default.json 또는 
/home/ubuntu/jenkins-data/updates/default.json (설치 방식에 따라)

sudo rm /var/lib/jenkins/updates/default.json 또는
rm /home/ubuntu/jenkins-data/updates/default.json (설치 방식에 따라)

# ※ 참고. ssh key 생성 (중요: ADD방식으로 처리 필요)
ssh-keygen -t rsa

cat id_rsa.pub >> ~/.ssh/authorized_keys


-----------------------------------------------------------------------------------------

## Nginx 연결 시 깨짐 현상 해결

sudo docker run -d \
  -p 8081:8080 \
  -p 50000:50000 \
  --name my-jenkins \
  -e JENKINS_OPTS="--prefix=/jenkins" \
  -v /home/ubuntu/jenkins-data:/var/jenkins_home \
  jenkins/jenkins:latest


## 조심해야 할 점
lts로 run 할 경우 Folder와 같은 플러그인이 설치 불가능(버전이 더 높아서)
버전을 latest로 하든지 해당 플러그인 버전을 확인하고 run 해줄 것
