package com.example.trashgg;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Players {
        private int record;
        private String name;

        public Players(String name)
        {
            this.name=name;
            this.record = 0;
        }
        public Players(String name, int record)
        {
            this.name=name;
            this.record = record;
        }

        public void setRecord(int record) {
            this.record = record;
        }

        public int getRecord() {
            return record;
        }


        public void setName(String name) {
            this.name = name;
        }


        public String getName() {
            return name;
        }

        public boolean newRecord(int r)
        {
            if(r>this.record)
            {
                this.record = r;
                return true;
            }
            else {
                return false;
            }
        }
    }

