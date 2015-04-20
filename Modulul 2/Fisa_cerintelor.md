Fisa Cerintelor
- Recommandation System , M2 –

Cuprins

1. Descriere

2. Domeniu 

3. Actionari/Interese 

4. Actori si obiective

5. Scenarii de utilizare

  5.1. Identificarea utilizatorului

  5.2. Adaugarea de interese de catre utilizator

  5.3. Stabilirea intereselor de scurta durata de catre aplicatie

  5.4. Stabilirea intereselor de lunga durata de catre aplicatie

Profesor coordonator :
- Iftene Adrian , PhD , decan al Facultatii de Informatica ,  Iasi


1. Descriere : 

Recommandation System este o aplicatie ce recomanda utilizatorului anumite articole, 
informatii si tutoriale pe baza intereseleor acestuia . 
Utilizatorul isi va crea un profil, va marca obiectele de interes si domeniile din care doreste informatii 
si aplicatie va face sugestii atat pe baza informatiilor de utilizator cat si sugestii 
ale prietenilor acestuia sau persoanele cu care impartaseste aceleasi interese .
Modulul nostru se va ocupa cu analiza datelor unui uitilizator
al unei retea de socializare si aflarea intereselor pe termen scurt si intereselor pe termen lung ale acestuia.

2. Domenii :
	
Pe baza datelor oferite de catre profilul utilizatorului putem afla preferintele acestuia pe o perioada mai scurta, 
dar si pe o perioada mai lunga. Utilizand aceste informatii putem recomanda locuri pe care le-ar putea vizita,
sau aplicatii pe care le-ar putea folosi din domeniul preferat de el.

3. Actionari/Interese :   

Modulul nostru va pune la indemana celorlalti membri ai proiectului informatii despre 
interesele pe termen scurt si lung ale utilizatorul aplicatiei.

4. Actori si Obiective :

Recommandation System va satisface nevoile clientului, iar Modulul 2 va avea sarcina de preluare a 
preferintele utilizatorului din retelele de socializare. Utilizatorul uman va fi satisfacut prin 
recomandarea unor informatii concrete si de mare precizie conform cerintelor acestuia. 

5. Scenarii de Utilizare : 

5.1. Identificarea utilizatorului

5.1.1. Obiective

Utilizatorul doreste sa se logheze la reteaua de socializare.

5.1.2. Pasi

1. Daca utilizatorul nu are cont isi va crea unul completand corespunzator campurile formularului de creare a contului. Dupa completarea formularului se va apasa butonul “Inregistrare”;

2. Daca utilizatorul are cont se va autentifica pe reteaua de socializare folosind e-mail-ul sau numele de utilizator si parola pe care le-a ales la completarea formularului de creare a contului si va apasa butonul “Log In”.

5.1.3 Extensii

1. In cazul completarii incorecte/incomplete a formularului de creare a contului se afiseaza un mesaj corespunzator.

2. In cazul completarii gresite a e-mail-ului/numelui de utilizator sau a parolei se afiseaza un mesaj corespunzator.

5.2. Adaugarea de interese de catre utilizator (tag-uri, postari, followers)

5.2.1. Obiectiv/Context

Utilizatorul își înregistreaza interesele pe baza API-urilor oferite de reteaua de socializare.

5.2.2. Pași

1. Dupa logare, utilizatorul are acces la newsfeed si la profilul sau. Îsi poate modifica informatiile de baza, 
și poate edita secțiunea de interese existenta pe pagina profilului. În secțiunea news feed vor apărea postări 
ale prietenilor săi, ale paginilor urmărite de acesta, sau pagini sugerate de către rețeaua de socializare, bazate pe interesele de scurta sau lunga durata.

2. Utilizatorul poate cauta o pagina de interes in secțiunea de newsfeed folosind bara de cautare sau poate cauta 
un domeniu de interes folosind sectiunea de editare a intereselor din cadrul paginii sale. De asemenea, utilizatorul 
cand viziteaza o pagina oarecare a retelei de socializare poate să o adauge la lista de interese apasand butonul "Urmărește".
Utilizatorul poate adauga la lista sa de interese si paginile de profil ale prietenilor sai, apasand același buton. 

5.2.3. Extensii

In cazul in care conexiunea este pierduta, se specifica acest lucru și se cere reintroducerea datelor.

5.3. Stabilirea intereselor de scurta durata de catre aplicatie

5.3.1. Obiectiv/Context : 

Aflarea intereselor de scurta durata.

5.3.2. Pasi:	

1. Se identifica interesele de scurta durata ale utilizatorului(pe durata ultimelori zile sau a unei saptamani)

2. Cu aceste informatii putem afla date despre activitatile desfasurate de utilizator in ultima perioada.
	
5.4. Stabilirea intereselor de lunga durata de catre aplicatiei

5.4.1 Obiective 

Se doreste identificarea intereselor de lunga durata ale utilizatorului in reteaua de socializare.

5.4.2 Pasi 

1. Se identifica interesele de scurta durata ale utilizatorului de-a lungul unei luni (30 de zile).

2. Pe baza lor, construindu-se niste criterii, se pot afla ariile de interes ale utilizatorului contului respectiv. Astfel, prin studierea si analizarea activitatii lui pe reteaua de socializare se pot stabili si construi interesele de lunga durata.

	




