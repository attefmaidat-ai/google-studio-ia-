/**
 * ROKA ELECTRO - Application Logic
 * E-commerce WhatsApp & Supabase Integration
 */

const PHONE_NUMBER = "0791708328";
const WHATSAPP_INTL = "213791708328";

// Données initiales locales des produits (garantissent un fonctionnement immédiat sans configuration préalable)
const INITIAL_PRODUCTS = [
  {
    id: "arcodym-cuisiniere-lf699",
    brand: "ARCODYM",
    name: "Cuisinière LF699-GGF50F Inox",
    categoryId: "cuisinieres",
    categoryName: "Cuisinières",
    image: "images/img_cuisiniere_arcodym.jpg",
    priceTag: "Prix sur demande",
    description: "Cuisinière tout inox professionnelle haute résistance. Dispose de 5 brûleurs puissants dont un triple couronne spécial cuisson rapide, allumage intégré automatique, grand four ventilé avec tournebroche et minuterie.",
    keySpecs: [
      "Finition 100% Acier Inoxydable brossé",
      "5 brûleurs à gaz haute efficacité (1 triple couronne)",
      "Grand four à gaz ventilé avec tournebroche",
      "Allumage électronique à une main",
      "Sécurité thermocouple totale table et four",
      "Garantie 12 mois avec service après-vente assuré"
    ],
    isPopular: true,
    inStock: true,
    warrantyMonths: 12
  },
  {
    id: "schallenge-robot-multifonction",
    brand: "SCHALLENGE",
    name: "Robot multifonction Schallenge",
    categoryId: "robots_mixeurs",
    categoryName: "Robots & Mixeurs",
    image: "images/img_robot_schallenge.jpg",
    priceTag: "Prix sur demande",
    description: "Le robot pétrin et préparateur multifonction indispensable pour vos pâtes, gâteaux, brioches et crèmes. Bol géant en acier inox de 7 litres, moteur ultra performant avec transmission métallique et bol blender en verre.",
    keySpecs: [
      "Moteur haute performance 1500 Watts",
      "Bol inox grande capacité 7 Litres avec poignée",
      "6 vitesses réglables + fonction Pulse",
      "Kit pâtisserie complet : Crochet, Batteur, Fouet",
      "Blender en verre trempé 1.5L inclus",
      "Garantie 12 mois ROKA ELECTRO"
    ],
    isPopular: true,
    inStock: true,
    warrantyMonths: 12
  },
  {
    id: "schallenge-robot-noir",
    brand: "SCHALLENGE",
    name: "Robot multifonction Schallenge Noir",
    categoryId: "robots_mixeurs",
    categoryName: "Robots & Mixeurs",
    image: "images/img_robot_schallenge.jpg",
    priceTag: "Prix sur demande",
    description: "Édition Deluxe Noire mate du célèbre robot multifonction Schallenge. Finition premium anti-traces, puissance renforcée, silencieux et ultra robuste pour une utilisation quotidienne et intensive.",
    keySpecs: [
      "Édition spéciale Black Matte élégante",
      "Moteur renforcé 1500 Watts ultra silencieux",
      "Bol en acier inoxydable poli 7 Litres",
      "Pieds ventouses anti-dérapants ultra stables",
      "Accessoires lavables au lave-vaisselle",
      "Garantie 12 mois SAV ROKA ELECTRO"
    ],
    isPopular: true,
    inStock: true,
    warrantyMonths: 12
  },
  {
    id: "midea-refrigerateur",
    brand: "MIDEA",
    name: "Réfrigérateur",
    categoryId: "refrigerateurs",
    categoryName: "Réfrigérateurs",
    image: "images/img_refrigerateur_midea.jpg",
    priceTag: "Prix sur demande",
    description: "Réfrigérateur moderne double porte Total No Frost par Midea. Économie d'énergie remarquable, froid ventilé homogène Multi Air Flow sans givre, clayettes en verre trempé réglables et compartiment fraîcheur fruits & légumes.",
    keySpecs: [
      "Technologie Total No Frost (zéro dégivrage)",
      "Système Multi-Air Flow circulation dynamique",
      "Finitions inox anti-traces de doigts",
      "Compresseur Inverter silencieux et économique",
      "Éclairage LED intérieur lumineux et économique",
      "Garantie 12 mois ROKA ELECTRO"
    ],
    isPopular: true,
    inStock: true,
    warrantyMonths: 12
  },
  {
    id: "arcodym-hachoir-dym-c022c",
    brand: "ARCODYM",
    name: "Hachoir DYM-C022C — 350 W",
    categoryId: "petit_electro",
    categoryName: "Petit électroménager",
    image: "images/chopper.svg",
    priceTag: "Prix sur demande",
    description: "Hachoir électrique polyvalent Arcodym 350 Watts. Équipé d'un bol en verre épais de 2 Litres et de 4 lames en acier inoxydable étagées pour hacher viande, oignons, noix et fines herbes en quelques secondes.",
    keySpecs: [
      "Puissance 350 Watts efficace",
      "Bol en verre transparent haute résistance 2 Litres",
      "4 lames inox amovibles bi-niveaux",
      "2 vitesses de hachage selon les aliments",
      "Base antidérapante en caoutchouc silicone",
      "Garantie 12 mois"
    ],
    isPopular: true,
    inStock: true,
    warrantyMonths: 12
  },
  {
    id: "raylan-cafetiere-expresso",
    brand: "RAYLAN",
    name: "Cafetière Expresso 15 Bar",
    categoryId: "petit_electro",
    categoryName: "Petit électroménager",
    image: "images/coffee.svg",
    priceTag: "Prix sur demande",
    description: "Machine expresso manuelle avec pompe pression 15 bars pour un café riche en arômes et une crème dorée onctueuse. Buse vapeur inox intégrée pour préparer cappuccinos et latte macchiato comme au café.",
    keySpecs: [
      "Pression de pompe italienne 15 Bars",
      "Buse vapeur orientable pour mousse de lait",
      "Filtre 1 ou 2 tasses compatible café moulu & dosettes",
      "Réservoir d'eau amovible transparent 1.25L",
      "Garantie 12 mois ROKA ELECTRO"
    ],
    isPopular: false,
    inStock: true,
    warrantyMonths: 12
  },
  {
    id: "brandt-airfryer-5l",
    brand: "BRANDT",
    name: "Friteuse Sans Huile Air Fryer 5.5L",
    categoryId: "petit_electro",
    categoryName: "Petit électroménager",
    image: "images/airfryer.svg",
    priceTag: "Prix sur demande",
    description: "Cuisinez croustillant avec jusqu'à 85% d'huile en moins ! Grande cuve de 5.5L familiale, panneau de commande digital tactile avec 8 pré-réglages automatiques pour frites, poulet, poissons et pâtisseries.",
    keySpecs: [
      "Capacité familiale 5.5 Litres (jusqu'à 6 personnes)",
      "Puissance 1700 Watts à convection rapide 360°",
      "8 programmes tactiles automatiques",
      "Thermostat réglable de 80°C à 200°C",
      "Garantie 12 mois ROKA ELECTRO"
    ],
    isPopular: false,
    inStock: true,
    warrantyMonths: 12
  }
];

// 58 Wilayas d'Algérie
const ALGERIAN_WILAYAS = [
  { code: 1, name: "Adrar", delay: "48h - 72h" },
  { code: 2, name: "Chlef", delay: "24h - 48h" },
  { code: 3, name: "Laghouat", delay: "24h - 48h" },
  { code: 4, name: "Oum El Bouaghi", delay: "24h - 48h" },
  { code: 5, name: "Batna", delay: "24h - 48h" },
  { code: 6, name: "Béjaïa", delay: "24h - 48h" },
  { code: 7, name: "Biskra", delay: "24h - 48h" },
  { code: 8, name: "Béchar", delay: "48h - 72h" },
  { code: 9, name: "Blida", delay: "24h Express" },
  { code: 10, name: "Bouira", delay: "24h - 48h" },
  { code: 11, name: "Tamanrasset", delay: "48h - 72h" },
  { code: 12, name: "Tébessa", delay: "24h - 48h" },
  { code: 13, name: "Tlemcen", delay: "24h - 48h" },
  { code: 14, name: "Tiaret", delay: "24h - 48h" },
  { code: 15, name: "Tizi Ouzou", delay: "24h - 48h" },
  { code: 16, name: "Alger", delay: "24h Express" },
  { code: 17, name: "Djelfa", delay: "24h - 48h" },
  { code: 18, name: "Jijel", delay: "24h - 48h" },
  { code: 19, name: "Sétif", delay: "24h - 48h" },
  { code: 20, name: "Saïda", delay: "24h - 48h" },
  { code: 21, name: "Skikda", delay: "24h - 48h" },
  { code: 22, name: "Sidi Bel Abbès", delay: "24h - 48h" },
  { code: 23, name: "Annaba", delay: "24h - 48h" },
  { code: 24, name: "Guelma", delay: "24h - 48h" },
  { code: 25, name: "Constantine", delay: "24h - 48h" },
  { code: 26, name: "Médéa", delay: "24h - 48h" },
  { code: 27, name: "Mostaganem", delay: "24h - 48h" },
  { code: 28, name: "M'Sila", delay: "24h - 48h" },
  { code: 29, name: "Mascara", delay: "24h - 48h" },
  { code: 30, name: "Ouargla", delay: "48h - 72h" },
  { code: 31, name: "Oran", delay: "24h Express" },
  { code: 32, name: "El Bayadh", delay: "48h - 72h" },
  { code: 33, name: "Illizi", delay: "72h" },
  { code: 34, name: "Bordj Bou Arréridj", delay: "24h - 48h" },
  { code: 35, name: "Boumerdès", delay: "24h Express" },
  { code: 36, name: "El Tarf", delay: "24h - 48h" },
  { code: 37, name: "Tindouf", delay: "72h" },
  { code: 38, name: "Tissemsilt", delay: "24h - 48h" },
  { code: 39, name: "El Oued", delay: "48h - 72h" },
  { code: 40, name: "Khenchela", delay: "24h - 48h" },
  { code: 41, name: "Souk Ahras", delay: "24h - 48h" },
  { code: 42, name: "Tipaza", delay: "24h Express" },
  { code: 43, name: "Mila", delay: "24h - 48h" },
  { code: 44, name: "Aïn Defla", delay: "24h - 48h" },
  { code: 45, name: "Naâma", delay: "48h - 72h" },
  { code: 46, name: "Aïn Témouchent", delay: "24h - 48h" },
  { code: 47, name: "Ghardaïa", delay: "48h - 72h" },
  { code: 48, name: "Relizane", delay: "24h - 48h" },
  { code: 49, name: "Timimoun", delay: "72h" },
  { code: 50, name: "Bordj Badji Mokhtar", delay: "72h" },
  { code: 51, name: "Ouled Djellal", delay: "48h - 72h" },
  { code: 52, name: "Béni Abbès", delay: "72h" },
  { code: 53, name: "In Salah", delay: "72h" },
  { code: 54, name: "In Guezzam", delay: "72h" },
  { code: 55, name: "Touggourt", delay: "48h - 72h" },
  { code: 56, name: "Djanet", delay: "72h" },
  { code: 57, name: "El M'Ghair", delay: "48h - 72h" },
  { code: 58, name: "El Meniaa", delay: "48h - 72h" }
];

// App State
let allProducts = [...INITIAL_PRODUCTS];
let currentCategory = null;
let currentSearch = "";
let favorites = new Set(JSON.parse(localStorage.getItem('roka_favorites') || '[]'));

// DOM Elements
const productsGrid = document.getElementById("productsGrid");
const searchInput = document.getElementById("searchInput");
const searchClearBtn = document.getElementById("searchClearBtn");
const filterChips = document.querySelectorAll(".filter-chip");
const resultsInfoBar = document.getElementById("resultsInfoBar");
const resultsCountText = document.getElementById("resultsCountText");
const btnResetFilters = document.getElementById("btnResetFilters");

// WhatsApp Links Generator
function getWhatsAppOrderLink(productName) {
  const message = `Bonjour Roka Electro, je souhaite commander : ${productName}`;
  return `https://wa.me/${WHATSAPP_INTL}?text=${encodeURIComponent(message)}`;
}

function getWhatsAppInquiryLink() {
  const message = "Bonjour Roka Electro, je souhaite demander des informations sur vos prix et produits.";
  return `https://wa.me/${WHATSAPP_INTL}?text=${encodeURIComponent(message)}`;
}

// Render Products
function renderProducts() {
  const filtered = allProducts.filter(p => {
    const matchCat = !currentCategory || p.categoryId === currentCategory;
    const query = currentSearch.toLowerCase().trim();
    const matchQuery = !query ||
      p.name.toLowerCase().includes(query) ||
      p.brand.toLowerCase().includes(query) ||
      p.categoryName.toLowerCase().includes(query) ||
      p.description.toLowerCase().includes(query);
    return matchCat && matchQuery;
  });

  // Results Bar
  if (currentSearch || currentCategory) {
    resultsInfoBar.style.display = "flex";
    resultsCountText.textContent = `${filtered.length} produit(s) trouvé(s)`;
  } else {
    resultsInfoBar.style.display = "none";
  }

  // Clear Grid
  productsGrid.innerHTML = "";

  if (filtered.length === 0) {
    productsGrid.innerHTML = `
      <div style="grid-column: 1/-1; text-align: center; padding: 40px 20px; background: white; border-radius: 16px; border: 1px solid #e2e8f0;">
        <div style="font-size: 40px; margin-bottom: 10px;">🔍</div>
        <h3 style="font-size: 16px; font-weight: 800; margin-bottom: 6px;">Aucun produit trouvé</h3>
        <p style="font-size: 12px; color: #64748b; margin-bottom: 16px;">Essayez un autre terme de recherche ou affichez toutes les catégories.</p>
        <button onclick="resetFilters()" style="background: #103C91; color: white; border: none; padding: 10px 20px; border-radius: 20px; font-weight: 700; font-size: 12px; cursor: pointer;">Voir tous les produits</button>
      </div>
    `;
    return;
  }

  filtered.forEach(product => {
    const isFav = favorites.has(product.id);
    const card = document.createElement("div");
    card.className = "product-card";
    card.innerHTML = `
      <div class="product-image-container" onclick="openProductModal('${product.id}')">
        <img src="${product.image}" alt="${product.brand} ${product.name}" loading="lazy" />
        <span class="brand-badge">${product.brand}</span>
        <button class="fav-btn ${isFav ? 'active' : ''}" onclick="toggleFavorite(event, '${product.id}')" title="Ajouter aux favoris">
          ${isFav ? '❤️' : '🤍'}
        </button>
        <span class="warranty-badge">🛡️ Garantie ${product.warrantyMonths} mois</span>
      </div>

      <div class="product-cat-tag">
        <span>${product.categoryName}</span>
        <span class="product-in-stock">● En stock</span>
      </div>

      <h3 class="product-name" onclick="openProductModal('${product.id}')">${product.name}</h3>

      <div class="product-price-row">
        <span class="price-pill">${product.priceTag}</span>
        <button class="btn-details-link" onclick="openProductModal('${product.id}')">Voir fiche →</button>
      </div>

      <a href="${getWhatsAppOrderLink(product.name)}" target="_blank" rel="noopener noreferrer" class="btn-whatsapp-order" onclick="onOrderClick('${product.name}')">
        <img src="images/whatsapp.svg" alt="" />
        <span>Commander sur WhatsApp</span>
      </a>
    `;
    productsGrid.appendChild(card);
  });
}

// Log inquiry to Supabase
function onOrderClick(productName) {
  if (typeof saveInquiryToSupabase === 'function') {
    saveInquiryToSupabase(productName, '', '', 'Commande initiée via le site web');
  }
}

// Favorites management
function toggleFavorite(event, productId) {
  event.stopPropagation();
  if (favorites.has(productId)) {
    favorites.delete(productId);
  } else {
    favorites.add(productId);
  }
  localStorage.setItem('roka_favorites', JSON.stringify(Array.from(favorites)));
  renderProducts();
}

// Reset filters
function resetFilters() {
  currentSearch = "";
  currentCategory = null;
  searchInput.value = "";
  searchClearBtn.style.display = "none";
  filterChips.forEach(chip => {
    chip.classList.toggle("active", chip.dataset.category === "all");
  });
  renderProducts();
}

// Setup Event Listeners
function setupEvents() {
  // Search
  searchInput.addEventListener("input", (e) => {
    currentSearch = e.target.value;
    searchClearBtn.style.display = currentSearch ? "block" : "none";
    renderProducts();
  });

  searchClearBtn.addEventListener("click", () => {
    searchInput.value = "";
    currentSearch = "";
    searchClearBtn.style.display = "none";
    renderProducts();
  });

  // Filter chips
  filterChips.forEach(chip => {
    chip.addEventListener("click", () => {
      filterChips.forEach(c => c.classList.remove("active"));
      chip.classList.add("active");
      const cat = chip.dataset.category;
      currentCategory = cat === "all" ? null : cat;
      renderProducts();
    });
  });

  btnResetFilters.addEventListener("click", resetFilters);

  // Category cards click
  document.querySelectorAll(".category-card").forEach(card => {
    card.addEventListener("click", () => {
      const catId = card.dataset.category;
      currentCategory = currentCategory === catId ? null : catId;
      filterChips.forEach(chip => {
        chip.classList.toggle("active", chip.dataset.category === (currentCategory || "all"));
      });
      renderProducts();
      document.getElementById("produits").scrollIntoView({ behavior: "smooth" });
    });
  });

  // Contact buttons
  document.getElementById("btnContactWhatsApp").href = getWhatsAppInquiryLink();
  document.getElementById("floatingWhatsAppBtn").href = getWhatsAppInquiryLink();
  document.getElementById("navWhatsAppBtn").href = getWhatsAppInquiryLink();
}

// Product Modal
function openProductModal(productId) {
  const product = allProducts.find(p => p.id === productId);
  if (!product) return;

  const modal = document.getElementById("productModal");
  document.getElementById("modalBrand").textContent = product.brand;
  document.getElementById("modalName").textContent = product.name;
  document.getElementById("modalCategory").textContent = product.categoryName.toUpperCase();
  document.getElementById("modalPrice").textContent = product.priceTag;
  document.getElementById("modalImage").src = product.image;
  document.getElementById("modalImage").alt = product.name;
  document.getElementById("modalDescription").textContent = product.description;

  const specsList = document.getElementById("modalSpecs");
  specsList.innerHTML = "";
  product.keySpecs.forEach(spec => {
    const li = document.createElement("li");
    li.style.cssText = "display: flex; gap: 8px; font-size: 12px; margin-bottom: 6px; color: #1e293b;";
    li.innerHTML = `<span style="color: #103C91; font-weight: bold;">✓</span> <span>${spec}</span>`;
    specsList.appendChild(li);
  });

  const btnOrder = document.getElementById("modalOrderBtn");
  btnOrder.href = getWhatsAppOrderLink(product.name);

  modal.classList.add("open");
}

function closeProductModal() {
  document.getElementById("productModal").classList.remove("open");
}

// Wilayas Modal
function openWilayasModal() {
  const modal = document.getElementById("wilayasModal");
  renderWilayasList(ALGERIAN_WILAYAS);
  modal.classList.add("open");
}

function closeWilayasModal() {
  document.getElementById("wilayasModal").classList.remove("open");
}

function renderWilayasList(list) {
  const container = document.getElementById("wilayasList");
  container.innerHTML = "";
  list.forEach(w => {
    const row = document.createElement("div");
    row.style.cssText = "display: flex; justify-content: space-between; align-items: center; padding: 8px 12px; background: #f8fafc; border-radius: 8px; margin-bottom: 6px; font-size: 13px;";
    row.innerHTML = `
      <div style="display: flex; align-items: center; gap: 8px;">
        <span style="display: inline-flex; align-items: center; justify-content: center; width: 24px; height: 24px; background: #e2e8f0; border-radius: 50%; font-size: 10px; font-weight: bold; color: #103C91;">${w.code}</span>
        <strong>${w.name}</strong>
      </div>
      <span style="color: #F21878; font-weight: 700; font-size: 11px;">${w.delay}</span>
    `;
    container.appendChild(row);
  });
}

function filterWilayas(query) {
  const q = query.toLowerCase().trim();
  const filtered = ALGERIAN_WILAYAS.filter(w => w.name.toLowerCase().includes(q) || w.code.toString().includes(q));
  renderWilayasList(filtered);
}

// Supabase Settings Modal
function openSupabaseModal() {
  const config = getSavedSupabaseConfig();
  document.getElementById("supabaseUrlInput").value = config.url || "";
  document.getElementById("supabaseKeyInput").value = config.anonKey || "";
  document.getElementById("supabaseModal").classList.add("open");
}

function closeSupabaseModal() {
  document.getElementById("supabaseModal").classList.remove("open");
}

function saveSupabaseSettings() {
  const url = document.getElementById("supabaseUrlInput").value.trim();
  const anonKey = document.getElementById("supabaseKeyInput").value.trim();
  localStorage.setItem(SUPABASE_CONFIG_KEY, JSON.stringify({ url, anonKey }));
  
  if (initSupabase()) {
    alert("Configuration Supabase enregistrée et connectée avec succès !");
    syncProductsWithSupabase();
  } else {
    alert("Paramètres enregistrés. La connexion sera tentée au prochain chargement.");
  }
  closeSupabaseModal();
}

async function syncProductsWithSupabase() {
  if (typeof loadProductsFromSupabase === 'function') {
    const data = await loadProductsFromSupabase();
    if (data && data.length > 0) {
      allProducts = data;
      renderProducts();
    }
  }
}

// Init App
window.addEventListener("DOMContentLoaded", async () => {
  setupEvents();
  renderProducts();
  
  // Essayer de charger depuis Supabase si configuré
  if (initSupabase()) {
    syncProductsWithSupabase();
  }
});
