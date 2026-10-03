-- ====================================================
-- ROKA ELECTRO - SUPABASE SQL SCHEMA
-- Exécutez ce script dans le SQL Editor de Supabase
-- ====================================================

-- 1. Création de la table des produits
CREATE TABLE IF NOT EXISTS public.products (
    id TEXT PRIMARY KEY,
    brand TEXT NOT NULL,
    name TEXT NOT NULL,
    category_id TEXT NOT NULL,
    category_name TEXT NOT NULL,
    image_url TEXT NOT NULL,
    price_tag TEXT DEFAULT 'Prix sur demande',
    description TEXT NOT NULL,
    key_specs JSONB NOT NULL DEFAULT '[]'::jsonb,
    is_popular BOOLEAN DEFAULT true,
    in_stock BOOLEAN DEFAULT true,
    warranty_months INT DEFAULT 12,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT timezone('utc'::text, now()) NOT NULL
);

-- 2. Activer Row Level Security (RLS)
ALTER TABLE public.products ENABLE ROW LEVEL SECURITY;

-- Permettre la lecture publique des produits (anonyme)
CREATE POLICY "Lecture publique des produits"
ON public.products
FOR SELECT
USING (true);

-- 3. Table des demandes / commandes WhatsApp (optionnel pour suivi dans Supabase)
CREATE TABLE IF NOT EXISTS public.inquiries (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_name TEXT,
    wilaya TEXT,
    contact_phone TEXT,
    message TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT timezone('utc'::text, now()) NOT NULL
);

ALTER TABLE public.inquiries ENABLE ROW LEVEL SECURITY;

CREATE POLICY "Insertion publique des demandes"
ON public.inquiries
FOR INSERT
WITH CHECK (true);

-- 4. Insertion des produits initiaux
INSERT INTO public.products (id, brand, name, category_id, category_name, image_url, price_tag, description, key_specs, is_popular, in_stock, warranty_months)
VALUES
(
    'arcodym-cuisiniere-lf699',
    'ARCODYM',
    'Cuisinière LF699-GGF50F Inox',
    'cuisinieres',
    'Cuisinières',
    'images/img_cuisiniere_arcodym.jpg',
    'Prix sur demande',
    'Cuisinière tout inox professionnelle haute résistance. Dispose de 5 brûleurs puissants dont un triple couronne spécial cuisson rapide, allumage intégré automatique, grand four ventilé avec tournebroche et minuterie.',
    '["Finition 100% Acier Inoxydable brossé", "5 brûleurs à gaz haute efficacité (1 triple couronne)", "Grand four à gaz ventilé avec tournebroche", "Allumage électronique à une main", "Sécurité thermocouple totale table et four", "Garantie 12 mois avec service après-vente assuré"]'::jsonb,
    true,
    true,
    12
),
(
    'schallenge-robot-multifonction',
    'SCHALLENGE',
    'Robot multifonction Schallenge',
    'robots_mixeurs',
    'Robots & Mixeurs',
    'images/img_robot_schallenge.jpg',
    'Prix sur demande',
    'Le robot pétrin et préparateur multifonction indispensable pour vos pâtes, gâteaux, brioches et crèmes. Bol géant en acier inox de 7 litres, moteur ultra performant avec transmission métallique et bol blender en verre.',
    '["Moteur haute performance 1500 Watts", "Bol inox grande capacité 7 Litres avec poignée", "6 vitesses réglables + fonction Pulse", "Kit pâtisserie complet : Crochet pétrisseur, Batteur plat, Fouet ballon", "Blender en verre trempé 1.5L inclus", "Garantie 12 mois ROKA ELECTRO"]'::jsonb,
    true,
    true,
    12
),
(
    'schallenge-robot-noir',
    'SCHALLENGE',
    'Robot multifonction Schallenge Noir',
    'robots_mixeurs',
    'Robots & Mixeurs',
    'images/img_robot_schallenge.jpg',
    'Prix sur demande',
    'Édition Deluxe Noire mate du célèbre robot multifonction Schallenge. Finition premium anti-traces, puissance renforcée, silencieux et ultra robuste pour une utilisation quotidienne et intensive.',
    '["Édition spéciale Black Matte élégante", "Moteur renforcé 1500 Watts ultra silencieux", "Bol en acier inoxydable poli 7 Litres", "Pieds ventouses anti-dérapants ultra stables", "Accessoires lavables au lave-vaisselle", "Garantie 12 mois SAV ROKA ELECTRO"]'::jsonb,
    true,
    true,
    12
),
(
    'midea-refrigerateur',
    'MIDEA',
    'Réfrigérateur',
    'refrigerateurs',
    'Réfrigérateurs',
    'images/img_refrigerateur_midea.jpg',
    'Prix sur demande',
    'Réfrigérateur moderne double porte Total No Frost par Midea. Économie d''énergie remarquable, froid ventilé homogène Multi Air Flow sans givre, clayettes en verre trempé réglables et compartiment fraîcheur fruits & légumes.',
    '["Technologie Total No Frost (zéro dégivrage)", "Système Multi-Air Flow circulation dynamique", "Finitions inox anti-traces de doigts", "Compresseur Inverter silencieux et économique", "Éclairage LED intérieur lumineux et économique", "Garantie 12 mois ROKA ELECTRO"]'::jsonb,
    true,
    true,
    12
),
(
    'arcodym-hachoir-dym-c022c',
    'ARCODYM',
    'Hachoir DYM-C022C — 350 W',
    'petit_electro',
    'Petit électroménager',
    'images/chopper.svg',
    'Prix sur demande',
    'Hachoir électrique polyvalent Arcodym 350 Watts. Équipé d''un bol en verre épais de 2 Litres et de 4 lames en acier inoxydable étagées pour hacher viande, oignons, noix et fines herbes en quelques secondes.',
    '["Puissance 350 Watts efficace", "Bol en verre transparent haute résistance 2 Litres", "4 lames inox amovibles bi-niveaux", "2 vitesses de hachage selon les aliments", "Base antidérapante en caoutchouc silicone", "Garantie 12 mois"]'::jsonb,
    true,
    true,
    12
),
(
    'raylan-cafetiere-expresso',
    'RAYLAN',
    'Cafetière Expresso 15 Bar',
    'petit_electro',
    'Petit électroménager',
    'images/coffee.svg',
    'Prix sur demande',
    'Machine expresso manuelle avec pompe pression 15 bars pour un café riche en arômes et une crème dorée onctueuse. Buse vapeur inox intégrée pour préparer cappuccinos et latte macchiato comme au café.',
    '["Pression de pompe italienne 15 Bars", "Buse vapeur orientable pour mousse de lait", "Filtre 1 ou 2 tasses compatible café moulu & dosettes", "Réservoir d''eau amovible transparent 1.25L", "Plateau chauffe-tasses supérieur en inox", "Garantie 12 mois ROKA ELECTRO"]'::jsonb,
    false,
    true,
    12
),
(
    'brandt-airfryer-5l',
    'BRANDT',
    'Friteuse Sans Huile Air Fryer 5.5L',
    'petit_electro',
    'Petit électroménager',
    'images/airfryer.svg',
    'Prix sur demande',
    'Cuisinez croustillant avec jusqu''à 85% d''huile en moins ! Grande cuve de 5.5L familiale, panneau de commande digital tactile avec 8 pré-réglages automatiques pour frites, poulet, poissons, viandes et pâtisseries.',
    '["Capacité familiale 5.5 Litres (jusqu''à 6 personnes)", "Puissance 1700 Watts à convection rapide 360°", "8 programmes tactiles automatiques", "Thermostat réglable de 80°C à 200°C", "Tiroir et panier anti-adhésifs amovibles", "Garantie 12 mois ROKA ELECTRO"]'::jsonb,
    false,
    true,
    12
)
ON CONFLICT (id) DO NOTHING;
