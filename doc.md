MICROSERVICES

mimari bir yaklaşımdır. dağıtık parçalar bütünü. tüm problemlerin çözümü değil ve yeni problemler getirebilir.
martin fowler. - önce monolith bir uygulama yapılsın daha sonra microservice'e adapte edilsin.
Yazılım mimarisi nedir?- geliştirme sürecinde takip edilen yöntemler. yazılım ilkesi, programlama dili ve yan teknolojiler.
mikroservis mimarisi net tanımlayablecek ifade yoktur. var olan mimariler ile tanımlamaya çalışılabilir.
Olabildiğince küçük servislerdir. bağımsız deployement yapılabilir olması gerekmektedir. başka projelere bağımlılık yaratmaması gerekmektedir.
servislerin birbirinden bağımsız olarak build olabilmesi gerekir. sunu sayılarının ve kaynaklarının ölçeklenebilir olması gerekiyor.

servislerin programlama dilleri farklı olabilir. aralarındaki anlaşma dili ortak olsun yeterki. http/gRPC
her servisin ayrı bir veri tabanı olması esneklik katar. kümülatif maliyeti engeller. her servisin db ihtiyacı olmayabilir
API/Rest API
pipline
resilience - dayanıklılık. herhangi bir yerde alınan hata sonrası genel product'ın çalışaya devam edebilme yetisi
fault isolation - tek bir servisten cevap gelmediğinde komple 500 dönmek yerine sadece o servis kapsamında null veya default response yollamak ve genel cevabın iletilmesini sağlamak
SOA- uygulama componentlerinin çeşitli protokoler ile ağ üzerinden iletişim kurması

CAP THEOREM NEDIR? 
Consistency -  data sync. verilerin senkronizasyonu sağlanmalı. reddis, rabbitmq kafka eventdriven. veri tutarlılığını göz önünde bulundur.
Availability - her istekte cevap alabilmeliyim. servislerden biri patlasa daha bile yinede geri kalan servislerden cevap alabilmeliyim. 
Partition Tolarance - servisler arası iletişim koptuğunda diğer sunucular çalışmaya devam etmeli.

ACID / BASE - acidde veri tutarlılığı çok önemli. ilişkisel veri tabanları. base'de ise veriye erişim daha öncelikli.

Microservislerde iletişim;
restapi http json - en çok kullanılan haberleşme yöntemi
grpc ve apache thrifht http6 denebilir
async - event driven
message broker - rabbitmq redis pub/sub

Load Balancing
1- Server side load balan.
kubernetes lb, traefik
2- Client Side Load balan.
spring cloud lb, grpc client side lb.

Loglama - zipkin spring fw, jaeger, logstash
Orchestration - k8s, azure, aws elastic container
Service Discovery - Netflix Eureka, Zookeeper, Consul (İstek trafiğini servislere dağıtır)
Async Communication - Kafka, rabbitmq, aws sqs, google cloud pub/sub
Hata ayıklama - hystrix, polly 

Resttemplate service discovery sorumluluğunu yerine getirir. Günümüzde bu işi eureka yapar.
Feign Client nedir?
Servislerimiz arasındaki iletişimi klasik yöntem diyebileceğimiz RestTemplate ile yaparak sağlabiliyoruz. RestTemplate ile yapılan iletişim yöntemini kullanarak metotlarımız içerisinde ilgili api isteğini kullanarak metotlarımızı bağımlı hale getiriyoruz.
OpenFeign kullanarak servis iletişimi interface templateler üzerinden yönetip, daha okunabilir ve configürasyonel olarak kullanabilmeyi sağlamaktayız.
Bu kullanabilirlik sayesinde çok fazla kullanılan servislerin yönetilebilmesi ve olası değişiklerde daha hızlı ve kolay müdahale edebilmemizi sağlamaktadır.

**Lombok kullanma. Autowired kullanma. third party yazılım kullanımı riskli.

Controller'ın görevi; security varsa ilk önce onu kontrol atar, ardından validate atar, ilgili isteği servise aktarır ve servisten gelen cevabı response entity şeklinde client'a yansıtır.

RestControllerAdvice anotasyonu exceptionları rest response içerisinde dönebilmemize yarar.
Yeni spring versionları sayesinde EnableEurekaClient anotasyonuna ihtiyaç kalmadı.
controllerda throw exception yapma bunun yerine exceptioncontroller oluştur.

Exception Handling - 
Stop Process Inform User / Create Default Value&Change the request ---> exception durumlarında iki süreçten bir tanesini seçmen gerekiyor.
iki süreç bir arada yürümez.

Spring Boot Actuator, uygulamaların production ortamına hazır özellikleri (health check, disk usage, heap dump vs.) otomatik aktifleştirir ve farklı HTTP endpoint’ ler ile etkileşimde bulunmayı sağlayan bir yapı sunar.

API GATEWAY - istemcilerle backend sunucuları / mikro servisler arasında duran bir API yönetim aracıdır. çeşitli kurallara göre uygun servislere yönlendiren bir ters vekil sunucusu (reverse proxy) olarak çalışır.istek sınırlandırma, istatistik, kimlik doğrulama, monitoring vs. V1 API’sini başka backend sunucuya, V2 API’sini başka bir backend sunucuya yönlendirebilirsiniz. Yanıtları belirli kurallara göre önbelleklemeniz gerekebilir. Cachelemeyi ayrı bir katmanda çalıştırarak sonuçları backend sunucuya hiç istek yapmadan doğrudan cache’den sunabilirsiniz.

Vault, uygulama sırlarını yönetmek ve UI, CLI veya HTTP API kullanarak hassas bilgileri korumak için kullanılır - örneğin: tokenler, şifreler, sertifikalar, şifreleme anahtarları vs. Vault tüm sırları saklamak için merkezi bir yerdir. (http://www.inanzzz.com/index.php/yazi/1v7j/hashicorp-vault-ile-uygulama-sirlarini-yonetme#:~:text=Vault%2C%20uygulama%20s%C4%B1rlar%C4%B1n%C4%B1%20y%C3%B6netmek%20ve,saklamak%20i%C3%A7in%20merkezi%20bir%20yerdir.)
├── docker-compose.yml
└── vault
    └── config
        └── config.json
        
spring cloud config avantajı; tüm configleri ortak bir noktadan yönetebilir ve değiştirebiliriz. programı yenide ayağa kaldırmadan actuator refresh ile file içerisinde yapılan değişkliği programa yedirebiliriz.
