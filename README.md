# 📱 Pokédex Mobile

Un Pokédex Android natif, fluide et moderne, développé en **Kotlin**, exploitant l'API publique Tyradex. L'application arbore une charte graphique épurée inspirée du design de la célèbre **Pokéball** (Rouge vif, Blanc pur et Anthracite).

---

## ✨ Fonctionnalités

* **Chargement Dynamique** : Récupération en temps réel de la liste globale des Pokémon via des appels API asynchrones (`LifecycleScope`).
* **Recherche Instantanée** : Barre de recherche textuelle réactive qui filtre la liste à chaque lettre ajoutée ou supprimée (`TextWatcher`).
* **Filtre par Type** : Système de puces horizontales (`ChipGroup`) généré dynamiquement à partir d'une `Enum` Kotlin pour trier les Pokémon selon leurs types.
* **Écran de Détail complet** : Vue approfondie incluant le sprite, les types, les mensurations, la génération ainsi qu'un affichage graphique des statistiques de base (HP, ATK, DEF, S.ATK) via des jauges de progression.
* **Résilience Réseau** : Gestion propre du mode hors-ligne ou des pannes d'API avec affichage d'un écran d'erreur interactif et d'un bouton "Réessayer".

---

## 🛠️ Technologies & Bibliothèques utilisées

* **Langage** : Kotlin 🚀
* **Architecture UI** : XML / ConstraintLayout / Material Design Components (`MaterialCardView`, `ChipGroup`, `TextInputLayout`)
* **Liaison de Données** : View Binding
* **Appels Réseau** : Retrofit / Tyradex API Client
* **Asynchronisme** : Kotlin Coroutines & Lifecycle KTX

---

## 🚀 Installation & Lancement

1. Clonez ce dépôt sur votre machine :

```bash
git clone https://github.com/votre-pseudo/pokedex-mobile.git

```

2. Ouvrez le projet dans **Android Studio**.
3. Laissez Gradle synchroniser et télécharger les dépendances nécessaires.
4. Connectez un émulateur ou un appareil Android physique.
5. Cliquez sur le bouton **Run** (Flèche verte) pour compiler et lancer l'application.