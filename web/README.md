# ROKA ELECTRO — Version Web (Vercel, Supabase & Google)

Ce dossier contient la version web complète, ultra-rapide et responsive de **ROKA ELECTRO**, strictement identique à l'application mobile Android.

---

## 🚀 1. Déploiement sur Vercel (En 1 minute)

### Option A : Déploiement direct depuis GitHub
1. Poussez ce dépôt sur votre compte GitHub.
2. Rendez-vous sur [vercel.com](https://vercel.com) et connectez votre compte GitHub.
3. Cliquez sur **Add New Project** et sélectionnez ce projet.
4. Dans les paramètres de build :
   - **Root Directory** : laissez la racine ou indiquez `web`
   - Le fichier `vercel.json` configuré à la racine s'occupe automatiquement du routage vers `/web`.
5. Cliquez sur **Deploy**. Votre site est en ligne immédiatement avec certificat SSL gratuit !

### Option B : Déploiement via le CLI Vercel
```bash
npm install -g vercel
cd web
vercel
```

---

## ⚡ 2. Connexion avec Supabase (Base de données)

Le site fonctionne immédiatement avec les données pré-intégrées, mais vous pouvez le connecter à **Supabase** pour gérer vos produits en direct :

1. Créez un projet gratuit sur [supabase.com](https://supabase.com).
2. Ouvrez le **SQL Editor** dans le tableau de bord Supabase.
3. Copiez-collez et exécutez le script fourni dans **`web/schema.sql`**. Cela va :
   - Créer la table `products` avec les permissions de lecture publique.
   - Insérer automatiquement les produits initiaux (Arcodym, Schallenge, Midea, etc.).
   - Créer la table `inquiries` pour recevoir les commandes.
4. Rendez-vous dans **Project Settings > API** sur Supabase et récupérez :
   - `Project URL`
   - `anon public key`
5. Ouvrez le site web déployé, cliquez sur le bouton **⚙️ Supabase** en haut à droite, collez vos identifiants et cliquez sur **Enregistrer & Synchroniser**.

---

## 🌐 3. Déploiement sur Google (Firebase Hosting)

Le fichier `firebase.json` est déjà configuré :

```bash
# 1. Installer Firebase CLI
npm install -g firebase-tools

# 2. Se connecter avec votre compte Google
firebase login

# 3. Initialiser et déployer
firebase init hosting
firebase deploy
```
Votre site sera accessible sur votre domaine Google Cloud `https://<votre-projet>.web.app`.

---

## 📱 Caractéristiques intégrées

- **Numéro WhatsApp officiel :** `0791708328` (+213791708328)
- **Message de commande prérempli :** `Bonjour Roka Electro, je souhaite commander : [nom du produit]`
- **Prix :** Tous affichés en « Prix sur demande »
- **Filtres instantanés :** Recherche en direct par nom ou marque, et par catégorie
- **Livraison :** Modale avec les 58 wilayas d'Algérie et délais d'expédition
- **Mobile-first :** 1 colonne sur smartphone, photos avec `object-fit: contain`
