L’application à développer doit assurer la gestion d’une conférence 
scientifique en tenant compte des divers éléments présentés ci-dessus. Cette application sera 
utilisée par : un administrateur (qui est le président de la conférence), les membres du comité
scientifique, et des utilisateurs (pouvant être de simples utilisateurs consultant la 
conférence, des auteurs d’articles soumis ou des participants inscrits). L’application doit 
permettre principalement d’assurer les fonctionnalités suivantes:
• Création d’une nouvelle conférence. L’administrateur (président de la conférence) doit
pouvoir créer une nouvelle conférence, saisir et modifier toutes les informations qui la 
concernent (institution organisatrice, président, titre, date, lieu, topics (les 
thématiques adressées), membres du comité scientifique, membres du comité 
d’organisation, date limite de la soumission d’articles, date limite des inscriptions, les 
montants des frais d’inscription, …). 
• Gestion des Conférenciers invités. L’administrateur (président de la conférence) doit
pouvoir ajouter, consulter, supprimer ou modifier des conférenciers invités. Pour chacun 
d’entre eux, le pays d’origine, leur institution et le titre de leur présentation seront 
demandés.
• Consultation de la Conférence. Tous les utilisateurs possibles (y compris, 
l’administrateur et les membres des comités) peuvent :
o consulter toutes les informations liées à la conférence. 
o imprimer (dans un fichier texte) l’appel à participation à la conférence résumant
les principales informations de la conférence.
o consulter les conférenciers invités.
• Soumission d’Articles. Un utilisateur peut soumettre un article (seulement si la date 
limite des soumissions n’a pas été dépassée). Pour soumettre un article, l’utilisateur doit 
préciser tous les auteurs de l’article avec leur nom, email et institution, le titre de 
l’article et le nom (avec emplacement) du fichier pdf contenant l’article complet. 
Lorsqu’un nouvel article est soumis, il est par défaut marqué comme NA (not assigned : 
pas encore assigné à un évaluateur). Il passera à l’état UE (under evaluation : évaluation 
en cours), lorsqu’il est affecté à un membre du comité scientifique. Il passera à l’état 
ACC (accepted) ou bien REJ (rejected) dès que l’évaluateur donne sa décision finale 
d’acceptation ou rejet. 
• Suivi d’Articles. L’application doit permettre à tout auteur de suivre son (ou ses) 
papier(s) en lui permettant de consulter son (ou leur) état.
• Affectation des articles. L’application doit permettre à l’administrateur de consulter 
l’état des papiers soumis, ainsi que d’affecter chaque article soumis et non encore 
Institut Supérieur de Gestion de Tunis Module POO Avancée
Département Informatique 2
ème Année BIS
affecté (NA) à un membre du comité scientifique pour qu’il assure son évaluation.
Chaque article affecté passera à l’état (UE).
• Evaluation des articles soumis. L’application doit permettre à chaque membre du 
comité scientifique de consulter l’ensemble des articles qui lui ont été affectés et de 
mettre pour chaque article une décision : ACC / REJ.
• Inscriptions de participants. Cette fonctionnalité donne la possibilité aux utilisateurs
de s’inscrire à la conférence (seulement si la date limite de l’inscription n’a pas été 
dépassée). Pour s’inscrire, un participant doit fournir son nom, email, institution, et 
préciser le mode de paiement des frais d’enregistrement.
• Stat et Info Générales. Tous les utilisateurs possibles de cette application (y compris 
l’administrateur et les membres des comités) peuvent consulter la liste des papiers 
acceptés (titres avec noms d’auteurs), le nombre total de papiers soumis, le 
pourcentage de papiers acceptés par rapport aux soumis. L’administrateur peut en plus,
imprimer dans un fichier texte, la liste de tous les participants (utilisateurs inscrits).
