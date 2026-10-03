/**
 * ROKA ELECTRO - Supabase Client Integration
 * Permet de synchroniser les produits et demandes depuis Supabase
 */

const SUPABASE_CONFIG_KEY = 'roka_supabase_config';

// Configuration par défaut (peut être configurée dans le modal de réglages)
let supabaseClient = null;

function getSavedSupabaseConfig() {
  try {
    const raw = localStorage.getItem(SUPABASE_CONFIG_KEY);
    return raw ? JSON.parse(raw) : { url: '', anonKey: '' };
  } catch (e) {
    return { url: '', anonKey: '' };
  }
}

function initSupabase() {
  const config = getSavedSupabaseConfig();
  if (config.url && config.anonKey && window.supabase) {
    try {
      supabaseClient = window.supabase.createClient(config.url, config.anonKey);
      console.log('Supabase connecté avec succès');
      return true;
    } catch (e) {
      console.warn('Erreur initialisation Supabase:', e);
    }
  }
  return false;
}

// Récupérer les produits depuis Supabase ou fallback local
async function loadProductsFromSupabase() {
  if (!supabaseClient) {
    initSupabase();
  }

  if (supabaseClient) {
    try {
      const { data, error } = await supabaseClient
        .from('products')
        .select('*')
        .order('is_popular', { ascending: false });

      if (!error && data && data.length > 0) {
        console.log(`Chargé ${data.length} produits depuis Supabase`);
        return data.map(item => ({
          id: item.id,
          brand: item.brand,
          name: item.name,
          categoryId: item.category_id,
          categoryName: item.category_name,
          image: item.image_url,
          priceTag: item.price_tag || 'Prix sur demande',
          description: item.description,
          keySpecs: Array.isArray(item.key_specs) ? item.key_specs : JSON.parse(item.key_specs || '[]'),
          isPopular: item.is_popular,
          inStock: item.in_stock,
          warrantyMonths: item.warranty_months || 12
        }));
      }
    } catch (err) {
      console.warn('Fallback sur les données locales suite à une erreur Supabase:', err);
    }
  }

  return null; // Utiliser la base de données locale
}

// Enregistrer une demande de commande dans Supabase
async function saveInquiryToSupabase(productName, wilaya = '', phone = '', message = '') {
  if (supabaseClient) {
    try {
      await supabaseClient.from('inquiries').insert([
        {
          product_name: productName,
          wilaya: wilaya,
          contact_phone: phone,
          message: message,
          created_at: new Date().toISOString()
        }
      ]);
    } catch (e) {
      console.warn('Impossible d\'enregistrer la demande dans Supabase:', e);
    }
  }
}
