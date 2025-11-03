# facturation
# Description du projet de FACTURATION
Ce projet est une application de gestion de factures inspirée du modèle OKAYO.
Elle permet de créer des produits, gérer les prix et les taux de TVA, créer des factures pour les clients et générer des factures au format PDF.
Le projet inclut le backend en Java Spring Boot avec une base de données PostgreSQL (hébergée sur Supabase).

# Stack technique
Backend : Java 17, Spring Boot, Spring Data JPA, Hibernate, Lombok
Base de données : PostgreSQL (Supabase)
PDF : iText (version com.lowagie.text)
API : REST pour la gestion des produits, TVA, prix et factures

# Fonctionnalités principales
# Produits
Ajouter un produit avec un prix et un taux de TVA sur une période donnée
Ajouter des prix ou des taux de TVA supplémentaires pour un produit existant
Vérification des doublons ou chevauchements de périodes

# Factures
Créer des factures pour un client
Une facture peut contenir plusieurs lignes de produits
Calcul automatique du Total HT et du Total TTC
Gestion du statut de la facture (BROUILLON / EMISE)
Validation d’une facture via l’API

# Génération de PDF de facture :
Tableau des produits avec quantité, prix HT, TVA, total HT
Totaux HT et TTC formatés avec séparateur de milliers
Les montants s’affichent en centimes correctement formatés

# Base de données
# Tables principales :
facture (id, reference, date, statut, totaux, client_id, emetteur_id)
ligne_facture (libelle_snapshot, pu_ht_snapshot_centimes, taux_tva_snapshot, quantite, total_ht_centimes)
catalogue_produit (nom, description)
prix_produit (prix_ht_centimes, valid_from, valid_to)
taux_tva (taux, valid_from, valid_to)

# Contraintes importantes :
Le statut de la facture est une énumération (BROUILLON ou EMISE)
Vérification des chevauchements pour prix et TVA lors de la création de produits

# Installation et exécution
# Backend
Cloner le projet
git clone <URL_DU_PROJET>
cd <Nom du projet>

# Configurer le fichier application.properties ou application.yml avec les informations PostgreSQL (Supabase) :
spring.datasource.url=jdbc:postgresql://<host>:5432/<db>
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.jpa.hibernate.ddl-auto=update

# Lancer le projet sur le bouton run

# API principales
# Produits

POST /api/produits → Créer un produit avec prix et TVA

POST /api/produits/{produitId}/prix → Ajouter un nouveau prix

POST /api/produits/{produitId}/tva → Ajouter un nouveau taux de TVA

GET /api/produits → Liste des produits

# Factures

POST /api/factures → Créer une facture

PUT /api/factures/{id}/EMISE → Valider une facture

GET /api/factures/{id}/pdf → Générer le PDF d’une facture

# Génération PDF

Le PDF est généré côté backend via la classe PdfService

Le PDF contient :

En-tête avec émetteur et client

Tableau des lignes de produits

Total HT et Total TTC formatés avec séparateur de milliers 

# Documentation API avec Swagger

L’application Spring Boot inclut Swagger pour documenter et tester les endpoints REST.

Accéder à Swagger UI

Lancer l’application 

Ouvrir un navigateur à l’adresse :
http://localhost:8081/swagger-ui.html

Il y'aura  la liste de toutes les API disponibles : produits, prix, TVA, factures, validation de facture, génération PDF, etc.

Fonctionnalités de Swagger

Tester directement les endpoints depuis l’interface

Voir les modèles (DTO et entities) utilisés par chaque endpoint

Vérifier les paramètres et réponses (status, body JSON)

Générer des exemples de requêtes et réponses

# Exemple

Pour créer une facture via Swagger :

Aller dans POST /api/factures

Cliquer sur Try it out

Remplir le JSON de la facture

Cliquer sur Execute pour tester la requête