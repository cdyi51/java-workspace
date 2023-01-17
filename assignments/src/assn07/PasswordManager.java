package assn07;

import java.util.*;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password123";
    private Account<K,V>[] _passwords;
    private int _size;

    public PasswordManager() {
        _size = 0;
        _passwords = new Account[50];
    }


    // TODO: put
    @Override
    public void put(K key, V value) {
        Account<K, V> newAccount = new Account<>(key, value);
        int index = Math.abs(key.hashCode()) % 50; // generate an index for newAccount
        if (_passwords[index] == null) { // if spot is empty (account doesn't exist), add newAccount at the index we've generated
            Account<K, V> current = _passwords[index];
            newAccount.setNext(current);
            _passwords[index] = newAccount;
            _size++;
        } else { // if spot is occupied (meaning account either exists or this is a collision), search for the matching key
            Set<K> keys = keySet();
            Account<K, V> current = _passwords[index];
            if (keys.contains(key)) { // if account already exists
                while (current != null) {
                    if (current.getWebsite().equals(key)) { // if current key is equal to input key, update value
                        current.setPassword(value);
                        return;
                    }// if not, move one "over" in spot's sub-map
                    current = current.getNext();
                }
            }
            else { // if collision, meaning the generated hashcode is already occupied by something else
                newAccount.setNext(current);
                _passwords[index] = newAccount;
                _size++;
            }
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        V value = null;
        int index = Math.abs(key.hashCode()) % 50;
        Account<K, V> account = _passwords[index];
        while (account!=null) {
            if(account.getWebsite().equals(key)) {
                value = account.getPassword();
                break;
            }
            account = account.getNext();
        }
        return value;
    }

    // TODO: size
    @Override
    public int size() {
        return _size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for(int i = 0; i < _passwords.length; i++) {
            if(_passwords[i]!=null) {
                Account<K,V> account = _passwords[i];
                while(account!=null) {
                    set.add(account.getWebsite());
                    account = account.getNext();
                }
            }
        }
        return set;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int index = Math.abs(key.hashCode()) % 50;
        Account<K, V> previous = null;
        Account<K, V> account = _passwords[index];
        while(account!=null) {
            if(account.getWebsite().equals(key)) {
                V oldValue = account.getPassword();
                if(previous==null) { // if key to remove is the first entry in _passwords
                    account = account.getNext();
                    _passwords[index] = account;
                } else {
                    previous.setNext(account.getNext());
                }
                _size--;
                return oldValue;
            }
            previous = account;
            account = account.getNext();
        }
        return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> duplicates = new ArrayList<>();
        for(int i = 0; i < _passwords.length; i++) {
            if(_passwords[i]!=null) {
                Account<K,V> account = _passwords[i];
                while(account!=null) {
                    if(account.getPassword().equals(value)) {
                        duplicates.add(account.getWebsite());
                    }
                    account = account.getNext();
                }
            }
        }
        return duplicates;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return enteredPassword.equals( MASTER_PASSWORD);
    }

    /*
    Generates random password of input length
     */
    @Override
    public String generateRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
