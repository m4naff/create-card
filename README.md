Create Card Project:
Proqramın məqsədi istifadəçilər üçün kard yaratmaqdır, hansıkı user lar qeydiyyatdan keçməli və ya login ola bilməli token lər vasitəsilə sorğular atmalıdı.

Bu proyektdə təhlükəsizlik üçün jwt authentication tətbiq olunmuşdur və istifadəçi sorğu göndərmək üçün non-opaque token lərdən istifadə etməlidir.
Card, Consumer, Order üçün status lar təyin edilmişdir:
Card status: əgər card statusu 0 qeyd olunubsa kard ya hələki aktivləşdirilmiyib yada kı  deaktivdir anlamına gəlir, 1 olmağı isə onun aktiv olmağı anlamına gəlir.
Consumer status: Consumer statusunun 1 olmağı onun uyğun olmağı anlamına gəlir, yəni ki card aktivləşdirməyı hazırdır. Onun 0 olması uyğun deyil anlamına, yəni hal hazırda order ində başqa user var mənasına gəlir.
Order status: Order statusunun 1 olmağı yeni order yaranmışdır, 0 olması isə order tamamlanmışdır deməkdir.
Bundan əlavə proyektə ms-notification əlavə olunmuşdur hansı ki, card order i yarandığı zaman Consumer ə bildiriş olaraq yeni order olduğu bildirilir.
Əlavə funksionallıqlar olaraq pagination və scheduler də əlavə olunmuşdur.Scheduler də lock mexanizmini tətbiq etmək üçün shedlock da istifə olunub.


Proqram ardıcıllığl başa düşülməsi üçün endpoint lərin məqsədləri aşağıda verilmişdir və ardıcıllıq necə tətbiq olunur göstərilmişdir.

1.
------- localhost:8080/users/sign-up ------------
 Proqramı işə saldıqdan sonra ilk iş userin qeydiyyatdan keçməsidir. Email və password a uyğun olaraq hesab yaradılır.

2.
------- localhost:8080/users/sign-in ------------
  İstifadəçi sign-up olduqdan sonra sorğular ata bilmək üçün token əldə etməlidir və sign-in endpoint dən istifadə edərək card yaratmaq mərhələsinə keçə bilər.

3.
  ------- localhost:8080/users/available-consumers ------------
   İstifadəçi card yaratmazdan öncə cardın hansı consumer üçün yarandığını görməli və card ı yaradarkən onun adını qeyd etməlidir. Bu endpoint vasitəsilə uyğun olan hər hansı consumer in adını götürüb
  card yaradarkənki sorğuda qeyd etməlidir.

4.
------- localhost:8080/users/create-card ------------
  Yuxarıdakı 3 mərhəlini yerinə yetirdikdən sonra istifadəçi card yaratmaq mərhələsinə çatır və o sadəcə mail adress ini və uyğun consumer adını qeyd edərək sorğu göndərməli. Order yaranarkən hansı consumer adı
  qeyd olunubsa, onun statusu uyğun deyil deyə qeyd olunur.

6.
------- localhost:8080/consumer/activate-card ------------
  Artıq bu əməliyyatlardan sonra card orderi yaranır və ms-notification vasitəsilə consumer ə notification göndərilir. Consumer user in card ını aktivləşdirməlidir. Əgər consumer bunu 12 saat ərzində
  yerine yetirməsə scheduler işə düşür və card ı avtomatik aktivləşdirir.(Card statusunun 1 olmağı aktiv dir anlamıdır, Order statusunun 0 olmağı artıq order tamamlanmışdır, Consumer  statusunun 1 olmağı uyğundur)

7.
------- localhost:8080/users/all-orders ------------
  İstifadəçi bu endpoint vasitəsilə order lərin səhifə sayı və element sayına uyğun axtarış edə bilər.
